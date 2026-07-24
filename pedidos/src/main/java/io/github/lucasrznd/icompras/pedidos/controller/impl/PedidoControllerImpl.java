package io.github.lucasrznd.icompras.pedidos.controller.impl;

import io.github.lucasrznd.icompras.pedidos.controller.PedidoController;
import io.github.lucasrznd.icompras.pedidos.dto.request.CreateNovoPagamentoRequest;
import io.github.lucasrznd.icompras.pedidos.dto.request.CreatePedidoRequest;
import io.github.lucasrznd.icompras.pedidos.dto.request.UpdatePedidoRequest;
import io.github.lucasrznd.icompras.pedidos.dto.response.PedidoResponse;
import io.github.lucasrznd.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class PedidoControllerImpl implements PedidoController {

    private final PedidoService service;

    @Override
    public ResponseEntity<Page<PedidoResponse>> findAll(UUID clienteId, Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(clienteId, pageable));
    }

    @Override
    public ResponseEntity<PedidoResponse> findById(UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Override
    public ResponseEntity<PedidoResponse> create(CreatePedidoRequest request) {
        return ResponseEntity.status(CREATED).body(service.create(request));
    }

    @Override
    public ResponseEntity<PedidoResponse> update(UUID id, UpdatePedidoRequest request) {
        return ResponseEntity.ok().body(service.update(id, request));
    }

    @Override
    public ResponseEntity<Void> adicionarNovoPagamento(UUID id, CreateNovoPagamentoRequest request) {
        service.adicionarNovoPagamento(id, request);
        return ResponseEntity.ok().build();
    }
}
