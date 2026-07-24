package io.github.lucasrznd.icompras.pedidos.controller.impl;

import io.github.lucasrznd.icompras.pedidos.controller.RecebimentoCallbackPagamentoController;
import io.github.lucasrznd.icompras.pedidos.dto.response.RecebimentoCallbackPagamentoResponse;
import io.github.lucasrznd.icompras.pedidos.service.RecebimentoCallbackPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecebimentoCallbackPagamentoControllerImpl implements RecebimentoCallbackPagamentoController {

    private final RecebimentoCallbackPagamentoService service;

    @Override
    public ResponseEntity<Void> atualizarStatusPagamento(RecebimentoCallbackPagamentoResponse response, String apiKey) {
        service.atualizarStatusPagamento(response, apiKey);
        return ResponseEntity.ok().build();
    }
}
