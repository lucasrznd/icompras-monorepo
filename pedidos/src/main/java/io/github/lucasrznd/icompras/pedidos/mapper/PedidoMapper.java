package io.github.lucasrznd.icompras.pedidos.mapper;

import io.github.lucasrznd.icompras.pedidos.dto.request.CreatePedidoRequest;
import io.github.lucasrznd.icompras.pedidos.dto.request.UpdatePedidoRequest;
import io.github.lucasrznd.icompras.pedidos.dto.response.PedidoResponse;
import io.github.lucasrznd.icompras.pedidos.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ItemPedidoMapper.class})
public interface PedidoMapper {

    PedidoResponse toResponse(Pedido pedido);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "valorTotal", ignore = true)
    @Mapping(target = "chavePagamento", ignore = true)
    @Mapping(target = "codigoRastreamento", ignore = true)
    @Mapping(target = "urlNf", ignore = true)
    @Mapping(target = "itens", ignore = true)
    Pedido toEntity(CreatePedidoRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clienteId", ignore = true)
    @Mapping(target = "itens", ignore = true)
    Pedido update(UpdatePedidoRequest request, @MappingTarget Pedido pedido);

}
