package io.github.lucasrznd.icompras.pedidos.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPedidoResponse(
        UUID id,
        UUID produtoId,
        Integer quantidade,
        BigDecimal valorUnitario
) {
}
