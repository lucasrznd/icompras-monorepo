package io.github.lucasrznd.faturamento.service;

import io.github.lucasrznd.faturamento.config.props.MinioProps;
import io.github.lucasrznd.faturamento.dtos.request.CreateFileRequest;
import io.github.lucasrznd.faturamento.dtos.response.FileNameResponse;
import io.github.lucasrznd.faturamento.dtos.response.FileUrlResponse;
import io.github.lucasrznd.icompras.common.exception.StorageException;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.Http;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class BucketService {

    private final MinioClient minioClient;
    private final MinioProps props;

    public FileNameResponse upload(CreateFileRequest request) {
        try {
            var object = PutObjectArgs.builder()
                    .bucket(props.getBucketName())
                    .object(request.file().getOriginalFilename())
                    .stream(request.file().getInputStream(), request.file().getSize(), -1L)
                    .contentType(request.file().getContentType())
                    .build();
            minioClient.putObject(object);
            return new FileNameResponse(request.file().getOriginalFilename());
        } catch (MinioException | IOException e) {
            log.error("[STORAGE] Falha ao enviar arquivo para o storage. {}", e.getMessage());
            throw new StorageException("storage.error.upload");
        }
    }

    public FileUrlResponse getUrl(String fileName) {
        try {
            var url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Http.Method.GET)
                            .bucket(props.getBucketName())
                            .expiry(10, TimeUnit.MINUTES)
                            .object(fileName)
                            .build()
            );

            return new FileUrlResponse(url);
        } catch (MinioException e) {
            log.error("[STORAGE] Falha ao gerar URL para o arquivo {}. {}", fileName, e.getMessage());
            throw new StorageException("storage.error.url");
        }
    }
}
