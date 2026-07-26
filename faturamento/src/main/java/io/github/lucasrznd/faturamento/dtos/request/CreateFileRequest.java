package io.github.lucasrznd.faturamento.dtos.request;

import jakarta.validation.constraints.NotNull;

public record CreateFileRequest(
        @NotNull(message = "O nome do arquivo é obrigatório.")
        String fileName,

        String contentType,

        @NotNull(message = "O conteúdo do arquivo é obrigatório.")
        byte[] content) {
}
