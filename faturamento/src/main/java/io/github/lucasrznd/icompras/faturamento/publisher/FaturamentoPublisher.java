package io.github.lucasrznd.icompras.faturamento.publisher;

import io.github.lucasrznd.icompras.faturamento.dtos.request.CreateAtualizacaoStatusPedidoRequest;
import io.github.lucasrznd.icompras.faturamento.dtos.response.PedidoResponse;
import io.github.lucasrznd.icompras.common.enums.PedidoStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
@RequiredArgsConstructor
public class FaturamentoPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${icompras.config.kafka.topics.pedidos-faturados}")
    private String topic;

    public void publish(PedidoResponse pedido, String urlNF) {
        var representation = new CreateAtualizacaoStatusPedidoRequest(
                pedido.id(),
                PedidoStatus.FATURADO,
                urlNF
        );

        try {
            var json = objectMapper.writeValueAsString(representation);
            kafkaTemplate.send(topic, "data", json);
        } catch (KafkaException | JacksonException e) {
            log.error("[Kafka] Erro ao publicar pedido faturado: {}", e.getMessage());
        }
    }
}
