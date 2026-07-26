package io.github.lucasrznd.icompras.logistica.subscriber;

import io.github.lucasrznd.icompras.logistica.dto.response.AtualizacaoFaturamentoRepresentation;
import io.github.lucasrznd.icompras.logistica.service.EnvioPedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
@RequiredArgsConstructor
public class FaturamentoSubscriber {

    private final ObjectMapper objectMapper;
    private final EnvioPedidoService service;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}",
            topics = "${icompras.config.kafka.topics.pedidos-faturados}")
    public void listen(String payload) {
        try {
            var json = objectMapper.readValue(payload, AtualizacaoFaturamentoRepresentation.class);

            service.send(json.id());
            log.info("Pedido processado com sucesso: {}", json.id());
        } catch (KafkaException | JacksonException e) {
            log.error("Erro ao receber Pedido faturado: {}", e.getMessage());
        }
    }
}
