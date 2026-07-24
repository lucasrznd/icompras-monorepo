package io.github.lucasrznd.icompras.pedidos.repository;

import io.github.lucasrznd.icompras.pedidos.entities.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    Page<Pedido> findAllByDeletedAtIsNull(Pageable pageable);

    Page<Pedido> findAllByClienteIdAndDeletedAtIsNull(UUID clienteId, Pageable pageable);

}
