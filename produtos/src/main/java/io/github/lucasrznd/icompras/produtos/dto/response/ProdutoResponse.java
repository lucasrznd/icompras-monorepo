package io.github.lucasrznd.icompras.produtos.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoResponse(
        UUID id,
        String nome,
        BigDecimal valorUnitario,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
}
