package io.github.lucasrznd.faturamento.dtos.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPedidoResponse(
        UUID produtoId,
        String nomeProduto,
        Integer quantidade,
        BigDecimal valorUnitario,
        BigDecimal total
) {
}
