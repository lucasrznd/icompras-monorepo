package io.github.lucasrznd.icompras.clientes.service;

import io.github.lucasrznd.icompras.clientes.dto.request.CreateClienteRequest;
import io.github.lucasrznd.icompras.clientes.dto.request.UpdateClienteRequest;
import io.github.lucasrznd.icompras.clientes.dto.response.ClienteResponse;
import io.github.lucasrznd.icompras.clientes.entity.Cliente;
import io.github.lucasrznd.icompras.clientes.mapper.ClienteMapper;
import io.github.lucasrznd.icompras.clientes.repository.ClienteRepository;
import io.github.lucasrznd.icompras.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public Page<ClienteResponse> findAll(Pageable pageable) {
        return repository.findAllByDeletedAtIsNull(pageable).map(mapper::toResponse);
    }

    public ClienteResponse findById(UUID id) {
        return mapper.toResponse(find(id));
    }

    public ClienteResponse create(final CreateClienteRequest request) {
        Cliente savedEntity = repository.save(mapper.toEntity(request));
        return mapper.toResponse(savedEntity);
    }

    public ClienteResponse update(UUID id, final UpdateClienteRequest request) {
        Cliente entity = find(id);
        Cliente productUpdated = mapper.update(request, entity);

        repository.save(productUpdated);
        return mapper.toResponse(productUpdated);
    }

    public void delete(UUID id) {
        Cliente entity = find(id);
        entity.setDeletedAt(LocalDateTime.now());
        repository.save(entity);
    }

    private Cliente find(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Cliente.class, id));
    }
}
