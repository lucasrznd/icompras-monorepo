package io.github.lucasrznd.icompras.logistica.dto.response;

import io.github.lucasrznd.icompras.common.enums.PedidoStatus;

import java.util.UUID;

public record AtualizacaoFaturamentoRepresentation(
        UUID id,
        PedidoStatus status,
        String urlNf
) {
}
