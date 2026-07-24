package io.github.lucasrznd.icompras.pedidos.client;

import io.github.lucasrznd.icompras.pedidos.client.representation.ProdutoRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "produtos", url = "${icompras.pedidos.clients.produtos.url}")
public interface ProdutoClient {

    @GetMapping("/{id}")
    ProdutoRepresentation findById(@PathVariable UUID id);

}
