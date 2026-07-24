package io.github.lucasrznd.icompras.pedidos.entities;

import io.github.lucasrznd.icompras.common.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item_pedido")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ItemPedido extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "pedido_id")
    @ManyToOne
    private Pedido pedido;

    @Column(name = "produto_id", nullable = false)
    private UUID produtoId;

    @Transient
    private String nomeProduto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "valor_unitario", precision = 16, scale = 2, nullable = false)
    private BigDecimal valorUnitario;

}
