package io.github.lucasrznd.icompras.pedidos.entities;

import io.github.lucasrznd.icompras.pedidos.enums.TipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosPagamento {

    private String dados;
    private TipoPagamento tipoPagamento;

}
