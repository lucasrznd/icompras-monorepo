package io.github.lucasrznd.icompras.pedidos.entities;

import io.github.lucasrznd.icompras.common.entity.Auditable;
import io.github.lucasrznd.icompras.common.enums.PedidoStatus;
import io.github.lucasrznd.icompras.pedidos.client.representation.ClienteRepresentation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Pedido extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "cliente_id", nullable = false)
    private UUID clienteId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private PedidoStatus status;

    @Column(name = "valor_total", precision = 16, scale = 2, nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "chave_pagamento", columnDefinition = "text")
    private String chavePagamento;

    @Column(name = "observacoes", columnDefinition = "text")
    private String observacoes;

    @Column(name = "codigo_rastreamento", length = 255)
    private String codigoRastreamento;

    @Column(name = "url_nf", columnDefinition = "text")
    private String urlNf;

    @Transient
    private DadosPagamento dadosPagamento;

    @Transient
    private ClienteRepresentation dadosCliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemPedido> itens = new ArrayList<>();

}
