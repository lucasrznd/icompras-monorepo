package io.github.lucasrznd.faturamento.controller;

import io.github.lucasrznd.faturamento.dtos.response.FileNameResponse;
import io.github.lucasrznd.faturamento.dtos.response.FileUrlResponse;
import io.github.lucasrznd.icompras.common.exception.StandardError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Tag(name = "BucketController", description = "Controller responsável por gerenciar os buckets")
@RequestMapping("/buckets")
public interface BucketController {

    @Operation(summary = "Fazer upload de um arquivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Arquivo enviado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class)))
    })
    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileNameResponse> upload(@RequestParam("file") @NotNull MultipartFile file);

    @Operation(summary = "Buscar URL de um arquivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "URL do arquivo gerada com sucesso", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = FileUrlResponse.class)
            )),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping("/{fileName}/url")
    ResponseEntity<FileUrlResponse> getUrl(@PathVariable String fileName);
}
