package io.github.lucasrznd.faturamento.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record CreateFileRequest(
        @NotNull(message = "O arquivo é obrigatório.")
        MultipartFile file) {
}
