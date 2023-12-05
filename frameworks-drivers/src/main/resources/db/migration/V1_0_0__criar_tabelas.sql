CREATE TABLE cliente (
    id  serial primary key,
    nome varchar(100) not null,
    cpf varchar(11) not null,
    email varchar(100) not null
);

CREATE TABLE produto (
    id  serial primary key,
    nome varchar(100) not null,
    preco float not null,
    categoria varchar(100) not null,
    descricao varchar(300) not null,
    imagem varchar(500) not null,
    ativo boolean not null default true
);

CREATE TABLE pedido (
    id serial primary key,
    cliente_id int null,
    etapa varchar(100) not null,
    status_pagamento varchar(100) not null,
    pedido_realizado_em timestamp not null,
    pedido_retirado_em timestamp,
    CONSTRAINT fk_cliente
    FOREIGN KEY(cliente_id)
    REFERENCES cliente(id)
);

CREATE TABLE pedido_produto (
    pedido_id int REFERENCES pedido(id),
    produto_id int REFERENCES produto(id),
    CONSTRAINT pedido_produto_id PRIMARY KEY (pedido_id, produto_id)
);

CREATE TABLE pagamento (
    id serial primary key,
    pedido_id int,
    codigo_de_autenticacao varchar(100) not null,
    data_do_pagamento timestamp not null,
    data_de_confirmacao timestamp,
    status varchar(100) not null,
    CONSTRAINT fk_pedido
    FOREIGN KEY(pedido_id)
    REFERENCES pedido(id)
);