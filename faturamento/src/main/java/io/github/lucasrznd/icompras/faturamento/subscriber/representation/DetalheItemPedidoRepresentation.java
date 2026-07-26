package io.github.lucasrznd.icompras.faturamento.subscriber.representation;

import java.math.BigDecimal;
import java.util.UUID;

public record DetalheItemPedidoRepresentation(
        UUID produtoId,
        String nomeProduto,
        Integer quantidade,
        BigDecimal valorUnitario,
        BigDecimal total
) {
}
