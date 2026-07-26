package io.github.lucasrznd.faturamento.controller.impl;

import io.github.lucasrznd.faturamento.controller.BucketController;
import io.github.lucasrznd.faturamento.dtos.request.CreateFileRequest;
import io.github.lucasrznd.faturamento.dtos.response.FileNameResponse;
import io.github.lucasrznd.faturamento.dtos.response.FileUrlResponse;
import io.github.lucasrznd.faturamento.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class BucketControllerImpl implements BucketController {

    private final BucketService service;

    @Override
    public ResponseEntity<FileNameResponse> upload(CreateFileRequest request) {
        return ResponseEntity.status(CREATED).body(service.upload(request));
    }

    @Override
    public ResponseEntity<FileUrlResponse> getUrl(String fileName) {
        return ResponseEntity.ok().body(service.getUrl(fileName));
    }
}
