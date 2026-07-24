package io.github.lucasrznd.icompras.pedidos.service;

import io.github.lucasrznd.icompras.common.exception.InvalidApiKeyException;
import io.github.lucasrznd.icompras.pedidos.dto.response.RecebimentoCallbackPagamentoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RecebimentoCallbackPagamentoService {

    private String apiKey;
    private PedidoService pedidoService;

    public RecebimentoCallbackPagamentoService(@Value("${banco.api-key}") String apiKey, PedidoService pedidoService) {
        this.apiKey = apiKey;
        this.pedidoService = pedidoService;
    }

    public void atualizarStatusPagamento(RecebimentoCallbackPagamentoResponse response, String apiKey) {
        if (!apiKey.equals(this.apiKey)) {
            throw new InvalidApiKeyException();
        }

        pedidoService.updateStatusPagamento(response.id(), response.chavePagamento(), response.status(), response.observacoes());
    }
}
