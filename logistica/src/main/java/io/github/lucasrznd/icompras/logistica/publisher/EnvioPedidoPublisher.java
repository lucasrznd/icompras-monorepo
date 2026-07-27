package io.github.lucasrznd.icompras.logistica.publisher;

import io.github.lucasrznd.icompras.logistica.dto.request.CreateAtualizacaoEnvioPedidoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnvioPedidoPublisher {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${icompras.config.kafka.topics.pedidos-enviados}")
    private String topic;

    public void send(CreateAtualizacaoEnvioPedidoRequest request) {
        try {
            String json = objectMapper.writeValueAsString(request);
            kafkaTemplate.send(topic, "data", json);
            log.info("[KAFKA] Pedido enviado com sucesso - Código: {}, Código de rastreio: {}", request.id(), request.codigoRastreamento());
        } catch (KafkaException e) {
            throw new KafkaException("[KAFKA] Erro ao enviar mensagem para o Kafka", e);
        }
    }
}
