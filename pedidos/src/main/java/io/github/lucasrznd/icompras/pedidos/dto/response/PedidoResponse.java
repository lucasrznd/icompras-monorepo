package io.github.lucasrznd.icompras.pedidos.dto.response;

import io.github.lucasrznd.icompras.pedidos.enums.PedidoStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoResponse(
        UUID id,
        UUID clienteId,
        PedidoStatus status,
        BigDecimal valorTotal,
        String chavePagamento,
        String observacoes,
        String codigoRastreamento,
        String urlNf,
        List<ItemPedidoResponse> itens,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
}
