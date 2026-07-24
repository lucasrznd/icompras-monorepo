package io.github.lucasrznd.icompras.pedidos.mapper;

import io.github.lucasrznd.icompras.pedidos.dto.request.CreateDadosPagamentoRequest;
import io.github.lucasrznd.icompras.pedidos.dto.response.DadosPagamentoResponse;
import io.github.lucasrznd.icompras.pedidos.entities.DadosPagamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DadosPagamentoMapper {

    DadosPagamentoResponse toResponse(final DadosPagamento dadosPagamento);

    DadosPagamento toEntity(final CreateDadosPagamentoRequest request);

}
