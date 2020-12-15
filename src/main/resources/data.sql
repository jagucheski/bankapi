INSERT INTO CLIENTE(id, tipo_pessoa) VALUES(1,'PF');
INSERT INTO CLIENTE(id, tipo_pessoa) VALUES(2,'PF');
INSERT INTO CLIENTE(id, tipo_pessoa) VALUES(3,'PF');
INSERT INTO CLIENTE(id, tipo_pessoa) VALUES(4,'PJ');
INSERT INTO CLIENTE(id, tipo_pessoa) VALUES(5,'PJ');

INSERT INTO CLIENTE_PESSOA_FISICA(id, cpf, nome, cliente_id) VALUES(1, '01143951011', 'JOAO',1);
INSERT INTO CLIENTE_PESSOA_FISICA(id, cpf, nome, cliente_id) VALUES(2, '02239151022', 'MARIA',2);
INSERT INTO CLIENTE_PESSOA_FISICA(id, cpf, nome, cliente_id) VALUES(3, '03343951033', 'JOSE',3);

INSERT INTO CLIENTE_PESSOA_JURIDICA(id, cnpj, razao_social, cliente_id) VALUES(4, '11372886002301', 'MVC SOFTWARE SA',4);
INSERT INTO CLIENTE_PESSOA_JURIDICA(id, cnpj, razao_social, cliente_id) VALUES(5, '22372886000302', 'BOOT SYSTEM LTDA',5);

INSERT INTO AGENCIA(id, numero) VALUES(1, 3693);
INSERT INTO AGENCIA(id, numero) VALUES(2, 1207);

INSERT INTO CONTA_CORRENTE(id, numero_conta, saldo_atual, agencia_id, cliente_id) VALUES(1,'11', '123.00', 1, 1);
INSERT INTO CONTA_CORRENTE(id, numero_conta, saldo_atual, agencia_id, cliente_id) VALUES(2,'22', '1323.00', 1, 2);
INSERT INTO CONTA_CORRENTE(id, numero_conta, saldo_atual, agencia_id, cliente_id) VALUES(3,'33', '9.95', 1, 3);

INSERT INTO CONTA_CORRENTE(id, numero_conta, saldo_atual, agencia_id, cliente_id) VALUES(4,'44', '99.95', 2, 4);
INSERT INTO CONTA_CORRENTE(id, numero_conta, saldo_atual, agencia_id, cliente_id) VALUES(5,'55', '4.95', 2, 5);
