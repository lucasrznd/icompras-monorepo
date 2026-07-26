package io.github.lucasrznd.icompras.pedidos.dto.response;

import io.github.lucasrznd.icompras.common.enums.PedidoStatus;

import java.util.UUID;

public record AtualizacaoStatusPedidoRepresentation(
        UUID id,
        PedidoStatus status,
        String urlNf,
        String codigoRastreio
) {
}
