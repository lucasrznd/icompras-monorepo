package io.github.lucasrznd.icompras.pedidos.dto.request;

import io.github.lucasrznd.icompras.pedidos.enums.TipoPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDadosPagamentoRequest(
        @NotBlank(message = "Os dados de pagamento são obrigatórios")
        String dados,

        @NotNull(message = "O tipo de pagamento é obrigatório")
        TipoPagamento tipoPagamento) {
}
