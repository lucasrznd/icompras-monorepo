package io.github.lucasrznd.faturamento.subscriber.representation;

import java.math.BigDecimal;
import java.util.UUID;

public record DetalheItemPedidoRepresentation(
        UUID produtoId,
        String nome,
        Integer quantidade,
        BigDecimal valorUnitario
) {
}
