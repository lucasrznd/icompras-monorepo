package io.github.lucasrznd.icompras.pedidos.subscriber;

import io.github.lucasrznd.icompras.pedidos.dto.response.AtualizacaoStatusPedidoRepresentation;
import io.github.lucasrznd.icompras.pedidos.service.AtualizacaoStatusPedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class AtualizacaoStatusPedidoSubscriber {

    private final AtualizacaoStatusPedidoService service;
    private final ObjectMapper objectMapper;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = {
            "${icompras.config.kafka.topics.pedidos-faturados}",
            "${icompras.config.kafka.topics.pedidos-enviados}"
    })
    public void receiveUpdate(String payload) {
        try {
            var atualizacaoStatus = objectMapper.readValue(payload, AtualizacaoStatusPedidoRepresentation.class);
            service.updateStatus(atualizacaoStatus);
        } catch (JacksonException e) {
            log.error("Erro ao desserializar a mensagem recebida do Kafka: {}", e.getMessage(), e);
        }
    }
}
