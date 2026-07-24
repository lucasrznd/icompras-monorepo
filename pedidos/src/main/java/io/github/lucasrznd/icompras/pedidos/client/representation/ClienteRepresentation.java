package io.github.lucasrznd.icompras.pedidos.client.representation;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClienteRepresentation(
        UUID id,
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
}
