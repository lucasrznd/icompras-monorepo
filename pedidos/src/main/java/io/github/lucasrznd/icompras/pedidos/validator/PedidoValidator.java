package io.github.lucasrznd.icompras.pedidos.validator;

import feign.FeignException;
import io.github.lucasrznd.icompras.common.exception.BusinessException;
import io.github.lucasrznd.icompras.common.exception.ResourceNotFoundException;
import io.github.lucasrznd.icompras.pedidos.client.ClienteClient;
import io.github.lucasrznd.icompras.pedidos.client.ProdutoClient;
import io.github.lucasrznd.icompras.pedidos.client.representation.ClienteRepresentation;
import io.github.lucasrznd.icompras.pedidos.client.representation.ProdutoRepresentation;
import io.github.lucasrznd.icompras.pedidos.dto.request.CreateItemPedidoRequest;
import io.github.lucasrznd.icompras.pedidos.dto.request.CreatePedidoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoValidator {

    private final ClienteClient clienteClient;
    private final ProdutoClient produtoClient;

    public void validate(CreatePedidoRequest request) {
        validarCliente(request);
        validarProduto(request);
    }

    private void validarProduto(CreatePedidoRequest request) {
        try {
            request.itens().stream().map(CreateItemPedidoRequest::produtoId).forEach(produtoClient::findById);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException(ProdutoRepresentation.class, e.request().requestTemplate().url());
        } catch (FeignException e) {
            throw new BusinessException("produto.service.unavailable");
        }
    }

    private void validarCliente(CreatePedidoRequest request) {
        try {
            clienteClient.findById(request.clienteId());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException(ClienteRepresentation.class, request.clienteId());
        } catch (FeignException e) {
            throw new BusinessException("produto.service.unavailable");
        }
    }
}
