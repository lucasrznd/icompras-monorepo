package io.github.lucasrznd.icompras.pedidos.mapper;

import io.github.lucasrznd.icompras.pedidos.entities.Pedido;
import io.github.lucasrznd.icompras.pedidos.publisher.representation.DetalhePedidoRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalhePedidoMapper {

    @Mapping(target = "nome", source = "dadosCliente.nome")
    @Mapping(target = "cpf", source = "dadosCliente.cpf")
    @Mapping(target = "logradouro", source = "dadosCliente.logradouro")
    @Mapping(target = "numero", source = "dadosCliente.numero")
    @Mapping(target = "bairro", source = "dadosCliente.bairro")
    @Mapping(target = "email", source = "dadosCliente.email")
    @Mapping(target = "telefone", source = "dadosCliente.telefone")
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    DetalhePedidoRepresentation toRepresentation(final Pedido pedido);

}
