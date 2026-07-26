package io.github.lucasrznd.icompras.logistica.service;

import io.github.lucasrznd.icompras.common.enums.PedidoStatus;
import io.github.lucasrznd.icompras.logistica.dto.request.CreateAtualizacaoEnvioPedidoRequest;
import io.github.lucasrznd.icompras.logistica.publisher.EnvioPedidoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnvioPedidoService {

    private final EnvioPedidoPublisher publisher;

    public void send(UUID id){
        var atualizacaoRepresentation = new CreateAtualizacaoEnvioPedidoRequest(
                id,
                PedidoStatus.ENVIADO,
                generateTrackingCode()
        );

        publisher.send(atualizacaoRepresentation);
    }

    private String generateTrackingCode() {
        RandomGenerator random = RandomGenerator.getDefault();

        String letters = random.ints(2, 'A', 'Z' + 1)
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());

        int number = random.nextInt(100_000_000, 1_000_000_000);

        return letters + number + "BR";
    }
}
