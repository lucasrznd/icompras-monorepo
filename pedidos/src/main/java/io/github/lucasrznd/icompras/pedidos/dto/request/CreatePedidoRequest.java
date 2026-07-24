package io.github.lucasrznd.icompras.pedidos.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreatePedidoRequest(
        @Schema(description = "ID do cliente", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        @NotNull(message = "O ID do cliente é obrigatório")
        UUID clienteId,

        @Schema(description = "Observações do pedido", example = "Entregar no período da tarde")
        String observacoes,

        @Schema(description = "Itens do pedido")
        @NotEmpty(message = "O pedido deve conter ao menos um item")
        @Valid
        List<CreateItemPedidoRequest> itens,

        @Schema(description = "Dados de pagamento do pedido")
        @NotNull(message = "Dados de pagamento são obrigatórios.")
        CreateDadosPagamentoRequest dadosPagamento
) {
}
