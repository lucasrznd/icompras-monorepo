package io.github.lucasrznd.icompras.pedidos.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateItemPedidoRequest(
        @Schema(description = "ID do produto", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        @NotNull(message = "O ID do produto é obrigatório")
        UUID produtoId,

        @Schema(description = "Quantidade do produto", example = "2")
        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
        Integer quantidade
) {
}
