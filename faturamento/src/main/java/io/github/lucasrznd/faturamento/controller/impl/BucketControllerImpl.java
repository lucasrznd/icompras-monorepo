package io.github.lucasrznd.faturamento.controller.impl;

import io.github.lucasrznd.faturamento.controller.BucketController;
import io.github.lucasrznd.faturamento.dtos.response.FileNameResponse;
import io.github.lucasrznd.faturamento.dtos.response.FileUrlResponse;
import io.github.lucasrznd.faturamento.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class BucketControllerImpl implements BucketController {

    private final BucketService service;

    @Override
    public ResponseEntity<FileNameResponse> upload(MultipartFile file) {
        return ResponseEntity.status(CREATED).body(service.upload(file));
    }

    @Override
    public ResponseEntity<FileUrlResponse> getUrl(String fileName) {
        return ResponseEntity.ok().body(service.getUrl(fileName));
    }
}
