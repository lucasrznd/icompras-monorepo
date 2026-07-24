package io.github.lucasrznd.faturamento.dtos.request;

import org.springframework.web.multipart.MultipartFile;

public record CreateFileRequest(String name, MultipartFile file) {
}
