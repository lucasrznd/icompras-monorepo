package io.github.lucasrznd.faturamento.dtos.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPedidoResponse(
        UUID produtoId,
        String nome,
        BigDecimal valorUnitario,
        Integer quantidade,
        BigDecimal total
) {
}
