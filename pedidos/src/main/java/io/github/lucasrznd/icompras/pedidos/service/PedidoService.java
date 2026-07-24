package io.github.lucasrznd.icompras.pedidos.service;

import feign.FeignException;
import io.github.lucasrznd.icompras.common.exception.BusinessException;
import io.github.lucasrznd.icompras.common.exception.ResourceNotFoundException;
import io.github.lucasrznd.icompras.pedidos.client.ClienteClient;
import io.github.lucasrznd.icompras.pedidos.client.ProdutoClient;
import io.github.lucasrznd.icompras.pedidos.client.ServicoBancarioClient;
import io.github.lucasrznd.icompras.pedidos.client.representation.ClienteRepresentation;
import io.github.lucasrznd.icompras.pedidos.client.representation.ProdutoRepresentation;
import io.github.lucasrznd.icompras.pedidos.dto.request.CreateNovoPagamentoRequest;
import io.github.lucasrznd.icompras.pedidos.dto.request.CreatePedidoRequest;
import io.github.lucasrznd.icompras.pedidos.dto.request.UpdatePedidoRequest;
import io.github.lucasrznd.icompras.pedidos.dto.response.PedidoResponse;
import io.github.lucasrznd.icompras.pedidos.entities.DadosPagamento;
import io.github.lucasrznd.icompras.pedidos.entities.ItemPedido;
import io.github.lucasrznd.icompras.pedidos.entities.Pedido;
import io.github.lucasrznd.icompras.pedidos.enums.PedidoStatus;
import io.github.lucasrznd.icompras.pedidos.mapper.ItemPedidoMapper;
import io.github.lucasrznd.icompras.pedidos.mapper.PedidoMapper;
import io.github.lucasrznd.icompras.pedidos.publisher.PagamentoPublisher;
import io.github.lucasrznd.icompras.pedidos.repository.ItemPedidoRepository;
import io.github.lucasrznd.icompras.pedidos.repository.PedidoRepository;
import io.github.lucasrznd.icompras.pedidos.validator.PedidoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final PedidoMapper mapper;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ItemPedidoMapper itemPedidoMapper;
    private final PedidoValidator validator;
    private final ServicoBancarioClient servicoBancarioClient;
    private final ProdutoClient produtoClient;
    private final ClienteClient clienteClient;
    private final PagamentoPublisher publisher;

    public Page<PedidoResponse> findAll(UUID clienteId, Pageable pageable) {
        if (clienteId != null) {
            return repository.findAllByClienteIdAndDeletedAtIsNull(clienteId, pageable).map(mapper::toResponse);
        }
        return repository.findAllByDeletedAtIsNull(pageable).map(mapper::toResponse);
    }

    public PedidoResponse findById(UUID id) {
        return mapper.toResponse(findPedidoCompleto(id));
    }

    @Transactional
    public PedidoResponse create(final CreatePedidoRequest request) {
        validator.validate(request);
        Pedido pedido = mapper.toEntity(request);
        persistirDados(request, pedido);
        enviarRequestPagamento(pedido);

        return mapper.toResponse(pedido);
    }

    public PedidoResponse update(UUID id, final UpdatePedidoRequest request) {
        Pedido pedido = find(id);
        mapper.update(request, pedido);
        return mapper.toResponse(repository.save(pedido));
    }

    @Transactional
    public void adicionarNovoPagamento(UUID id, CreateNovoPagamentoRequest request) {
        Pedido pedido = find(id);

        DadosPagamento dadosPagamento = new DadosPagamento(request.dados(), request.tipoPagamento());
        pedido.setDadosPagamento(dadosPagamento);
        pedido.setStatus(PedidoStatus.REALIZADO);

        String novaChavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(novaChavePagamento);

        repository.save(pedido);
    }

    public void updateStatusPagamento(UUID id, String chavePagamento, boolean status, String observacoes) {
        Pedido pedido = findPedidoCompleto(id);

        pedido.setChavePagamento(chavePagamento);

        if (status) {
            pedido.setStatus(PedidoStatus.PAGO);
            publisher.publish(pedido);
        } else {
            pedido.setStatus(PedidoStatus.ERRO_PAGAMENTO);
            pedido.setObservacoes(observacoes);
        }

        repository.saveAndFlush(pedido);
    }

    private ProdutoRepresentation fetchProduto(UUID produtoId) {
        try {
            return produtoClient.findById(produtoId);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException(ProdutoRepresentation.class, produtoId);
        } catch (FeignException e) {
            throw new BusinessException("produto.service.unavailable");
        }
    }

    private Pedido find(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Pedido.class, id));
    }

    private Pedido findPedidoCompleto(UUID id) {
        Pedido pedido = find(id);

        pedido.setDadosCliente(fetchCliente(pedido.getClienteId()));
        pedido.setItens(pedido.getItens()
                .stream().peek(item -> item.setNomeProduto(fetchProduto(item.getProdutoId()).nome())).toList()
        );

        return pedido;
    }

    private ClienteRepresentation fetchCliente(UUID clienteId) {
        try {
            return clienteClient.findById(clienteId);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException(ClienteRepresentation.class, clienteId);
        } catch (FeignException e) {
            throw new BusinessException("cliente.service.unavailable");
        }
    }

    private void persistirDados(CreatePedidoRequest request, Pedido pedido) {
        pedido.setStatus(PedidoStatus.REALIZADO);

        List<ItemPedido> itens = request.itens().stream()
                .map(itemRequest -> {
                    ItemPedido item = itemPedidoMapper.toEntity(itemRequest);
                    item.setPedido(pedido);
                    item.setValorUnitario(fetchProduto(itemRequest.produtoId()).valorUnitario());
                    return item;
                })
                .toList();

        pedido.setItens(itens);
        pedido.setValorTotal(calcularValorTotal(itens));
        repository.save(pedido);
        itemPedidoRepository.saveAll(itens);
    }

    private void enviarRequestPagamento(Pedido pedido) {
        var chavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(chavePagamento);
    }

    private BigDecimal calcularValorTotal(List<ItemPedido> itens) {
        return itens.stream()
                .map(item -> item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
