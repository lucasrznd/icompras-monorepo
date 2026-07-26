package io.github.lucasrznd.faturamento.dtos.response;

public record ClienteResponse(
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone
) {
}
