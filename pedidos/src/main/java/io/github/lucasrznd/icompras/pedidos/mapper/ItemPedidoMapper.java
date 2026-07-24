package io.github.lucasrznd.icompras.pedidos.mapper;

import io.github.lucasrznd.icompras.pedidos.dto.request.CreateItemPedidoRequest;
import io.github.lucasrznd.icompras.pedidos.dto.response.ItemPedidoResponse;
import io.github.lucasrznd.icompras.pedidos.entities.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    ItemPedidoResponse toResponse(ItemPedido item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "valorUnitario", ignore = true)
    ItemPedido toEntity(CreateItemPedidoRequest request);

}
