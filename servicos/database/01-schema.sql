CREATE
DATABASE icomprasclientes;

\connect icomprasclientes

CREATE TABLE clientes
(
    id         uuid PRIMARY KEY         DEFAULT gen_random_uuid(),
    nome       varchar(150)   NOT NULL,
    cpf        char(11)       NOT NULL,
    logradouro varchar(100),
    numero     varchar(10),
    bairro     varchar(100),
    email      varchar(150),
    telefone   varchar(20),
    created_at timestamp with time zone DEFAULT now(),
    updated_at timestamp,
    updated_by uuid,
    deleted_at timestamp
);

CREATE
DATABASE icomprasprodutos;

\connect icomprasprodutos

CREATE TABLE produtos
(
    id             uuid PRIMARY KEY         DEFAULT gen_random_uuid(),
    nome           varchar(100)   NOT NULL,
    descricao      text,
    valor_unitario decimal(16, 2) NOT NULL,
    created_at     timestamp with time zone DEFAULT now(),
    updated_at     timestamp,
    updated_by     uuid,
    deleted_at     timestamp
);


CREATE
DATABASE icompraspedidos;

\connect icompraspedidos

CREATE TABLE pedido
(
    id                  uuid PRIMARY KEY         DEFAULT gen_random_uuid(),
    cliente_id          uuid           NOT NULL,
    chave_pagamento     text,
    observacoes         text,
    status              varchar(20) CHECK (status IN
                                           ('REALIZADO', 'PAGO', 'FATURADO', 'ENVIADO', 'ENTREGUE', 'ERRO_PAGAMENTO',
                                            'PREPARANDO_ENVIO') ),
    valor_total         decimal(16, 2) NOT NULL,
    codigo_rastreamento varchar(255),
    url_nf              text,
    created_at          timestamp WITH TIME ZONE DEFAULT now(),
    updated_at          timestamp,
    updated_by          uuid,
    deleted_at          timestamp
);

CREATE TABLE item_pedido
(
    id             uuid PRIMARY KEY         DEFAULT gen_random_uuid(),
    pedido_id      uuid           NOT NULL REFERENCES pedido (id),
    produto_id     uuid           NOT NULL,
    quantidade     integer        NOT NULL,
    valor_unitario decimal(16, 2) NOT NULL,
    created_at     timestamp with time zone DEFAULT now(),
    updated_at     timestamp,
    updated_by     uuid,
    deleted_at     timestamp
);