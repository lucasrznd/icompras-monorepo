package io.github.lucasrznd.icompras.faturamento.dtos.response;

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
