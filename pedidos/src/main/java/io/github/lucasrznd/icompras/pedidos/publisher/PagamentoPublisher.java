package io.github.lucasrznd.icompras.pedidos.publisher;

import io.github.lucasrznd.icompras.pedidos.entities.Pedido;
import io.github.lucasrznd.icompras.pedidos.mapper.DetalhePedidoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class PagamentoPublisher {

    private final DetalhePedidoMapper mapper;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${icompras.config.kafka.topics.pedidos-pagos}")
    private String pagamentoTopic;

    public void publish(Pedido pedido) {
        log.info("Publicando evento de pagamento para o pedido: {}", pedido.getId());

        try {
            String message = objectMapper.writeValueAsString(mapper.toRepresentation(pedido));
            kafkaTemplate.send(pagamentoTopic, "data", message);
            log.info("Evento de pagamento publicado com sucesso para o pedido: {}", pedido.getId());
        } catch (JacksonException e) {
            log.error("Erro ao publicar evento de pagamento para o pedido: {}", pedido.getId(), e);
        }
    }
}
