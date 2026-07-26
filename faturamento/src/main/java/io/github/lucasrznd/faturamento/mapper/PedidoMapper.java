package io.github.lucasrznd.faturamento.mapper;

import io.github.lucasrznd.faturamento.dtos.response.PedidoResponse;
import io.github.lucasrznd.faturamento.subscriber.representation.DetalhePedidoRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(target = "cliente.nome", source = "nome")
    @Mapping(target = "cliente.cpf", source = "cpf")
    @Mapping(target = "cliente.logradouro", source = "logradouro")
    @Mapping(target = "cliente.numero", source = "numero")
    @Mapping(target = "cliente.bairro", source = "bairro")
    @Mapping(target = "cliente.email", source = "email")
    @Mapping(target = "cliente.telefone", source = "telefone")
    @Mapping(target = "total", source = "valorTotal")
    PedidoResponse toResponse(final DetalhePedidoRepresentation representation);

}
