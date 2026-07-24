package io.github.lucasrznd.icompras.pedidos.client;

import io.github.lucasrznd.icompras.pedidos.client.representation.ClienteRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "clientes", url = "${icompras.pedidos.clients.clientes.url}")
public interface ClienteClient {

    @GetMapping("/{id}")
    ClienteRepresentation findById(@PathVariable UUID id);

}
