package io.github.lucasrznd.faturamento.service;

import io.github.lucasrznd.faturamento.dtos.request.CreateFileRequest;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BucketService {

    private final MinioClient minioClient;

    public void upload(CreateFileRequest request) {

    }

    public void getUrl(String fileName) {

    }
}
