package io.github.lucasrznd.icompras.pedidos.client.representation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoRepresentation(
        UUID id,
        String nome,
        BigDecimal valorUnitario,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
}
