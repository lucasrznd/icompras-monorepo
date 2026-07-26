package io.github.lucasrznd.icompras.pedidos.dto.request;

import io.github.lucasrznd.icompras.common.enums.PedidoStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdatePedidoRequest(
        @Schema(description = "Status do pedido", example = "PAGO")
        PedidoStatus status,

        @Schema(description = "Chave de pagamento", example = "pix-abc-123")
        String chavePagamento,

        @Schema(description = "Código de rastreamento", example = "BR123456789BR")
        String codigoRastreamento,

        @Schema(description = "URL da nota fiscal", example = "https://nf.example.com/nf-123.pdf")
        String urlNf
) {
}
