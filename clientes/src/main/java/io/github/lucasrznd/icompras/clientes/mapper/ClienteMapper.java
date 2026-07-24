package io.github.lucasrznd.icompras.clientes.mapper;

import io.github.lucasrznd.icompras.clientes.dto.request.CreateClienteRequest;
import io.github.lucasrznd.icompras.clientes.dto.request.UpdateClienteRequest;
import io.github.lucasrznd.icompras.clientes.dto.response.ClienteResponse;
import io.github.lucasrznd.icompras.clientes.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponse toResponse(Cliente cliente);

    @Mapping(target = "id", ignore = true)
    Cliente toEntity(CreateClienteRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cpf", ignore = true)
    Cliente update(UpdateClienteRequest request, @MappingTarget Cliente cliente);

}
