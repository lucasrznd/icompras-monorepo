package io.github.lucasrznd.icompras.clientes.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateClienteRequest(
        @Schema(description = "Nome completo do cliente", example = "João da Silva")
        @NotBlank(message = "O nome do cliente é obrigatório")
        @Size(max = 150, message = "O nome deve conter no máximo 150 caracteres")
        String nome,

        @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
        @Size(max = 100, message = "O logradouro deve conter no máximo 100 caracteres")
        String logradouro,

        @Schema(description = "Número do endereço", example = "123")
        @Size(max = 10, message = "O número deve conter no máximo 10 caracteres")
        String numero,

        @Schema(description = "Bairro do endereço", example = "Centro")
        @Size(max = 100, message = "O bairro deve conter no máximo 100 caracteres")
        String bairro,

        @Schema(description = "E-mail do cliente", example = "joao@email.com")
        @Email(message = "O e-mail informado é inválido")
        @Size(max = 150, message = "O e-mail deve conter no máximo 150 caracteres")
        String email,

        @Schema(description = "Telefone do cliente", example = "11999999999")
        @Size(max = 20, message = "O telefone deve conter no máximo 20 caracteres")
        String telefone
) {
}
