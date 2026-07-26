package io.github.lucasrznd.icompras.pedidos.publisher.representation;

import java.math.BigDecimal;
import java.util.UUID;

public record DetalheItemPedidoRepresentation(
        UUID produtoId,
        String nomeProduto,
        Integer quantidade,
        BigDecimal valorUnitario
) {

    public BigDecimal getTotal() {
        return valorUnitario.multiply(new BigDecimal(quantidade));
    }
}
