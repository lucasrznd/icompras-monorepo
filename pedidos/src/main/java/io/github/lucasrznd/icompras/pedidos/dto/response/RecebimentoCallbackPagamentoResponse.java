package io.github.lucasrznd.icompras.pedidos.dto.response;

import java.util.UUID;

public record RecebimentoCallbackPagamentoResponse(
        UUID id,
        String chavePagamento,
        boolean status,
        String observacoes
) {
}
