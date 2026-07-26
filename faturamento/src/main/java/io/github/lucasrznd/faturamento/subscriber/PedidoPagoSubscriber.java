package io.github.lucasrznd.faturamento.subscriber;

import io.github.lucasrznd.faturamento.mapper.PedidoMapper;
import io.github.lucasrznd.faturamento.service.GeradorNotaFiscalService;
import io.github.lucasrznd.faturamento.service.NotaFiscalReportService;
import io.github.lucasrznd.faturamento.subscriber.representation.DetalhePedidoRepresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
@RequiredArgsConstructor
public class PedidoPagoSubscriber {

    private final ObjectMapper objectMapper;
    private final PedidoMapper mapper;
    private final GeradorNotaFiscalService service;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${icompras.config.kafka.topics.pedidos-pagos}")
    public void listen(String json) {
        try {
            log.info("Pedido pago recebido: {}", json);
            var pedido = objectMapper.readValue(json, DetalhePedidoRepresentation.class);
            var pedidoResponse = mapper.toResponse(pedido);
            service.generate(pedidoResponse);
        } catch (Exception e) {
            log.error("Erro ao processar pedido pago: {}", e.getMessage());
        }
    }
}
