package io.github.lucasrznd.faturamento.service;

import io.github.lucasrznd.faturamento.dtos.response.PedidoResponse;
import org.springframework.stereotype.Service;

@Service
public class GeradorNFService {

    public void generate(PedidoResponse pedido) {
        // Lógica para gerar a nota fiscal com base no pedido
        System.out.println("Gerando nota fiscal para o pedido: " + pedido.id());
    }
}
