package io.github.lucasrznd.icompras.pedidos.repository;

import io.github.lucasrznd.icompras.pedidos.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {

    List<ItemPedido> findByPedidoId(UUID pedidoId);

}
