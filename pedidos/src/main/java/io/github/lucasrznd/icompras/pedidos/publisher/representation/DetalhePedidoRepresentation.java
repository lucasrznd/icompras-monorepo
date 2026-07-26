package io.github.lucasrznd.icompras.pedidos.publisher.representation;

import io.github.lucasrznd.icompras.common.enums.PedidoStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record DetalhePedidoRepresentation(
        UUID id,
        UUID clienteId,
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone,
        String createdAt,
        BigDecimal valorTotal,
        PedidoStatus status,
        List<DetalheItemPedidoRepresentation> itens
) {
}
