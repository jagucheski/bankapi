INSERT INTO CLIENTE(id, tipo_pessoa) VALUES(1,'PF');
INSERT INTO CLIENTE(id, tipo_pessoa) VALUES(2,'PF');
INSERT INTO CLIENTE(id, tipo_pessoa) VALUES(3,'PF');
INSERT INTO CLIENTE(id, tipo_pessoa) VALUES(4,'PJ');
INSERT INTO CLIENTE(id, tipo_pessoa) VALUES(5,'PJ');

INSERT INTO PESSOA_FISICA(id, cpf,nome,cliente_id) VALUES(1, '011.439.510-11', 'JOAO',1);
INSERT INTO PESSOA_FISICA(id, cpf,nome,cliente_id) VALUES(2, '022.391.510-22', 'MARIA',2);
INSERT INTO PESSOA_FISICA(id, cpf,nome,cliente_id) VALUES(3, '033.439.510-33', 'JOSE',3);

INSERT INTO PESSOA_JURIDICA(id, cnpj,razao_social,cliente_id) VALUES(4, '11.372.886/0023-01', 'MVC SOFTWARE',4);
INSERT INTO PESSOA_JURIDICA(id, cnpj,razao_social,cliente_id) VALUES(5, '22.372.886/0003-02', 'BOOT SOFTWARE',5);

INSERT INTO AGENCIA(id, numero) VALUES(1,3693);
INSERT INTO AGENCIA(id, numero) VALUES(2,1207);

INSERT INTO CONTA_CORRENTE(id, numero_conta, saldo_atual, agencia_id, cliente_id) VALUES(1,'03.8836-06', '123.00', 1, 1);
INSERT INTO CONTA_CORRENTE(id, numero_conta, saldo_atual, agencia_id, cliente_id) VALUES(2,'01.1235-08', '1323.00', 1, 2);
INSERT INTO CONTA_CORRENTE(id, numero_conta, saldo_atual, agencia_id, cliente_id) VALUES(3,'03.8844-16', '49.95', 1, 3);

INSERT INTO CONTA_CORRENTE(id, numero_conta, saldo_atual, agencia_id, cliente_id) VALUES(4,'03.8844-16', '49.95', 2, 4);
INSERT INTO CONTA_CORRENTE(id, numero_conta, saldo_atual, agencia_id, cliente_id) VALUES(5,'03.8844-16', '49.95', 2, 5);