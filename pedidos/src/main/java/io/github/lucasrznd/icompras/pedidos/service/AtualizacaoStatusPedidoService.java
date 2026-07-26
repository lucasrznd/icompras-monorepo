package io.github.lucasrznd.icompras.pedidos.service;

import io.github.lucasrznd.icompras.pedidos.dto.response.AtualizacaoStatusPedidoRepresentation;
import io.github.lucasrznd.icompras.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizacaoStatusPedidoService {

    private final PedidoRepository repository;

    public void updateStatus(AtualizacaoStatusPedidoRepresentation representation) {
        // Implement the logic to update the pedido status
    }
}
