package io.github.lucasrznd.icompras.logistica.service;

import io.github.lucasrznd.icompras.common.enums.PedidoStatus;
import io.github.lucasrznd.icompras.logistica.dto.request.CreateAtualizacaoEnvioPedidoRequest;
import io.github.lucasrznd.icompras.logistica.publisher.EnvioPedidoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.github.lucasrznd.icompras.logistica.utils.TrackingUtil.generateTrackingCode;

@Service
@RequiredArgsConstructor
public class EnvioPedidoService {

    private final EnvioPedidoPublisher publisher;

    public void send(UUID id) {
        var atualizacaoRepresentation = new CreateAtualizacaoEnvioPedidoRequest(
                id,
                PedidoStatus.ENVIADO,
                generateTrackingCode()
        );

        publisher.send(atualizacaoRepresentation);
    }
}
