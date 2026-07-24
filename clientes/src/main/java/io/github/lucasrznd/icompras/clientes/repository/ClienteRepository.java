package io.github.lucasrznd.icompras.clientes.repository;

import io.github.lucasrznd.icompras.clientes.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Page<Cliente> findAllByDeletedAtIsNull(Pageable pageable);


}
