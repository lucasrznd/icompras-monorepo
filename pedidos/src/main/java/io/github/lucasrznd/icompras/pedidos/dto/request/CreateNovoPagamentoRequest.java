package io.github.lucasrznd.icompras.pedidos.dto.request;

import io.github.lucasrznd.icompras.pedidos.enums.TipoPagamento;

public record CreateNovoPagamentoRequest(
        String dados,
        TipoPagamento tipoPagamento
) {
}
