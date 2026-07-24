package io.github.lucasrznd.icompras.produtos.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateProdutoRequest(
        @Schema(name = "Nome", example = "Coca-Cola 2L", description = "O nome do produto", required = true)
        @NotBlank(message = "O nome do produto é obrigatório")
        @Size(max = 100, message = "O nome do produto deve conter no máximo 100 caracteres")
        String nome,

        @Schema(name = "Valor Unitário", example = "10.99", description = "O valor unitário do produto", required = true)
        @PositiveOrZero(message = "O valor unitário deve ser um número positivo ou zero")
        BigDecimal valorUnitario
) {
}
