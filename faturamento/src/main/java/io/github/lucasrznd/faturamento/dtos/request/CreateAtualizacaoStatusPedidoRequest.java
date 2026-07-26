package io.github.lucasrznd.faturamento.dtos.request;

import io.github.lucasrznd.icompras.common.enums.PedidoStatus;

import java.util.UUID;

public record CreateAtualizacaoStatusPedidoRequest(UUID id, PedidoStatus status, String urlNf) {
}
