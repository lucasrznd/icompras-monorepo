package io.github.lucasrznd.icompras.pedidos.controller;

import io.github.lucasrznd.icompras.common.exception.StandardError;
import io.github.lucasrznd.icompras.pedidos.dto.response.RecebimentoCallbackPagamentoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "RecebimentoCallbackPagamentoController", description = "Controller responsável pelos webhooks de pagamento")
@RequestMapping("/pedidos/callback-pagamentos")
public interface RecebimentoCallbackPagamentoController {

    @Operation(summary = "Atualizar status de pagamento do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Page.class)
            )),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class)))
    })
    @PostMapping
    ResponseEntity<Void> atualizarStatusPagamento(@RequestBody RecebimentoCallbackPagamentoResponse response, @RequestHeader(name = "X-Api-Key") String apiKey);

}
