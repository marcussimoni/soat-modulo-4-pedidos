INSERT INTO produto(nome, preco, descricao, categoria, imagem) VALUES ('Hamburger', 10.0, 'Hamburger', 'LANCHE', 'imagem do hamburger');
INSERT INTO produto(nome, preco, descricao, categoria, imagem) VALUES ('Batata', 5.90, 'Batata Pequena', 'ACOMPANHAMENTO', 'imagem da batata');
INSERT INTO produto(nome, preco, descricao, categoria, imagem) VALUES ('Batata', 6.90, 'Batata Média', 'ACOMPANHAMENTO', 'imagem da batata');
INSERT INTO produto(nome, preco, descricao, categoria, imagem) VALUES ('Batata', 7.90, 'Batata Grande', 'ACOMPANHAMENTO', 'imagem da batata');
INSERT INTO produto(nome, preco, descricao, categoria, imagem) VALUES ('Refrigerante', 5.50, 'Refrigerante Pequeno', 'BEBIDA', 'imagem da bebida');
INSERT INTO produto(nome, preco, descricao, categoria, imagem) VALUES ('Refrigerante', 6.50, 'Refrigerante Média', 'BEBIDA', 'imagem da bebida');
INSERT INTO produto(nome, preco, descricao, categoria, imagem) VALUES ('Refrigerante', 7.50, 'Refrigerante Grande', 'BEBIDA', 'imagem da bebida');

INSERT INTO CLIENTE(nome, email, cpf) VALUES('teste1', 'teste1@email.com', '11111111111');
INSERT INTO CLIENTE(nome, email, cpf) VALUES('teste2', 'teste2@email.com', '22222222222');

insert into pedido(etapa, pedido_realizado_em, status_pagamento) values('RECEBIDO', CURRENT_TIMESTAMP, 'AGUARDANDO_PAGAMENTO');
insert into pedido_produto values(1, 1);
insert into pedido_produto values(1, 3);
insert into pedido_produto values(1, 6);

insert into pedido(etapa, pedido_realizado_em, cliente_id, status_pagamento) values('RECEBIDO', CURRENT_TIMESTAMP, 1, 'AGUARDANDO_PAGAMENTO');
insert into pedido_produto values(2, 1);
insert into pedido_produto values(2, 4);
insert into pedido_produto values(2, 7);
