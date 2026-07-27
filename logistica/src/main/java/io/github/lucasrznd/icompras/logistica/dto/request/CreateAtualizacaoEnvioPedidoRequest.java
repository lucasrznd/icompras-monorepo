package io.github.lucasrznd.icompras.logistica.dto.request;

import io.github.lucasrznd.icompras.common.enums.PedidoStatus;

import java.util.UUID;

public record CreateAtualizacaoEnvioPedidoRequest(
        UUID id,
        PedidoStatus status,
        String codigoRastreamento
) {
}
