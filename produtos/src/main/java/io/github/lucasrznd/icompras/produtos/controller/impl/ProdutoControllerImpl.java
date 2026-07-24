package io.github.lucasrznd.icompras.produtos.controller.impl;

import io.github.lucasrznd.icompras.produtos.controller.ProdutoController;
import io.github.lucasrznd.icompras.produtos.dto.request.CreateProdutoRequest;
import io.github.lucasrznd.icompras.produtos.dto.request.UpdateProdutoRequest;
import io.github.lucasrznd.icompras.produtos.dto.response.ProdutoResponse;
import io.github.lucasrznd.icompras.produtos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class ProdutoControllerImpl implements ProdutoController {

    private final ProdutoService service;

    @Override
    public ResponseEntity<Page<ProdutoResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @Override
    public ResponseEntity<ProdutoResponse> findById(UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Override
    public ResponseEntity<ProdutoResponse> create(CreateProdutoRequest request) {
        return ResponseEntity.status(CREATED).body(service.create(request));
    }

    @Override
    public ResponseEntity<ProdutoResponse> update(UUID id, UpdateProdutoRequest request) {
        return ResponseEntity.ok().body(service.update(id, request));
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
