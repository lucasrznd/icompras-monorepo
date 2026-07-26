package io.github.lucasrznd.icompras.pedidos.dto.response;

import io.github.lucasrznd.icompras.common.enums.TipoPagamento;

public record DadosPagamentoResponse(String dados, TipoPagamento tipoPagamento) {
}
