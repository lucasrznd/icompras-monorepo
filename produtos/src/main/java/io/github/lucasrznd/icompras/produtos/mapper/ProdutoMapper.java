package io.github.lucasrznd.icompras.produtos.mapper;

import io.github.lucasrznd.icompras.produtos.dto.request.CreateProdutoRequest;
import io.github.lucasrznd.icompras.produtos.dto.request.UpdateProdutoRequest;
import io.github.lucasrznd.icompras.produtos.dto.response.ProdutoResponse;
import io.github.lucasrznd.icompras.produtos.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoResponse toResponse(final Produto produto);

    @Mapping(target = "id", ignore = true)
    Produto toEntity(final CreateProdutoRequest request);

    @Mapping(target = "id", ignore = true)
    Produto update(final UpdateProdutoRequest request, @MappingTarget Produto produto);

}
