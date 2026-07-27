package io.github.lucasrznd.icompras.pedidos.service;

import io.github.lucasrznd.icompras.pedidos.dto.response.AtualizacaoStatusPedidoRepresentation;
import io.github.lucasrznd.icompras.pedidos.entities.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtualizacaoStatusPedidoService {

    private final PedidoService service;

    @Transactional
    public void updateStatus(AtualizacaoStatusPedidoRepresentation representation) {
        Pedido pedido = service.find(representation.id());

        pedido.setStatus(representation.status());
        if (representation.urlNf() != null) pedido.setUrlNf(representation.urlNf());
        if (representation.codigoRastreamento() != null) pedido.setCodigoRastreamento(representation.codigoRastreamento());
    }
}
