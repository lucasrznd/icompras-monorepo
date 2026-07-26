package io.github.lucasrznd.icompras.faturamento.dtos.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PedidoResponse(
        UUID id,
        ClienteResponse cliente,
        String createdAt,
        BigDecimal total,
        List<ItemPedidoResponse> itens
) {
}
