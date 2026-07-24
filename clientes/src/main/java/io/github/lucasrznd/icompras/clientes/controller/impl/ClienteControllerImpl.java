package io.github.lucasrznd.icompras.clientes.controller.impl;

import io.github.lucasrznd.icompras.clientes.controller.ClienteController;
import io.github.lucasrznd.icompras.clientes.dto.request.CreateClienteRequest;
import io.github.lucasrznd.icompras.clientes.dto.request.UpdateClienteRequest;
import io.github.lucasrznd.icompras.clientes.dto.response.ClienteResponse;
import io.github.lucasrznd.icompras.clientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class ClienteControllerImpl implements ClienteController {

    private final ClienteService service;

    @Override
    public ResponseEntity<Page<ClienteResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @Override
    public ResponseEntity<ClienteResponse> findById(UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<ClienteResponse> create(CreateClienteRequest request) {
        return ResponseEntity.status(CREATED).body(service.create(request));
    }

    @Override
    public ResponseEntity<ClienteResponse> update(UUID id, UpdateClienteRequest request) {
        return ResponseEntity.ok().body(service.update(id, request));
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
