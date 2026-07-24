package io.github.lucasrznd.icompras.produtos.service;

import io.github.lucasrznd.icompras.common.exception.ResourceNotFoundException;
import io.github.lucasrznd.icompras.produtos.dto.request.CreateProdutoRequest;
import io.github.lucasrznd.icompras.produtos.dto.request.UpdateProdutoRequest;
import io.github.lucasrznd.icompras.produtos.dto.response.ProdutoResponse;
import io.github.lucasrznd.icompras.produtos.entity.Produto;
import io.github.lucasrznd.icompras.produtos.mapper.ProdutoMapper;
import io.github.lucasrznd.icompras.produtos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public Page<ProdutoResponse> findAll(Pageable pageable) {
        return repository.findAllByDeletedAtIsNull(pageable).map(mapper::toResponse);
    }

    public ProdutoResponse findById(UUID id) {
        return mapper.toResponse(find(id));
    }

    public ProdutoResponse create(final CreateProdutoRequest request) {
        Produto savedEntity = repository.save(mapper.toEntity(request));
        return mapper.toResponse(savedEntity);
    }

    public ProdutoResponse update(UUID id, final UpdateProdutoRequest request) {
        Produto entity = find(id);
        Produto productUpdated = mapper.update(request, entity);

        repository.save(productUpdated);
        return mapper.toResponse(productUpdated);
    }

    public void delete(UUID id) {
        Produto entity = find(id);
        entity.setDeletedAt(LocalDateTime.now());
        repository.save(entity);
    }

    private Produto find(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Produto.class, id));
    }
}
