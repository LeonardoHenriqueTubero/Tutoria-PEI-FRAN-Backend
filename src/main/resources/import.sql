INSERT INTO tb_endereco (rua, bairro, cidade, cep) VALUES ('rua A', 'bairro A', 'cidade A', '123456789');
INSERT INTO tb_endereco (rua, bairro, cidade, cep) VALUES ('rua B', 'bairro B', 'cidade B', '987654321');
INSERT INTO tb_endereco (rua, bairro, cidade, cep) VALUES ('rua C', 'bairro C', 'cidade C', '345678912');

INSERT INTO tb_usuario (cpf, nome) VALUES (123456789, 'Rodrigo Soares');
INSERT INTO tb_usuario (cpf, nome) VALUES (987654321, 'Matheus César');

INSERT INTO tb_dados_familia (pai, mae, responsavel, estrutura_familiar, num_pai, num_mae, num_responsavel) VALUES ('José Mendes', 'Andréia Mendes', '', 'separados, mora com a mãe', 987654321, 987654321, NULL);
INSERT INTO tb_dados_familia (pai, mae, responsavel, estrutura_familiar, num_pai, num_mae, num_responsavel) VALUES ('Nélio Alves', 'Carla Alves', 'Henrique Alves', 'separados, mora com o avô', NULL, NULL, 234567890);
INSERT INTO tb_dados_familia (pai, mae, responsavel, estrutura_familiar, num_pai, num_mae, num_responsavel) VALUES ('Carlos de Souza', 'Maria de Souza', '', 'casados', 123456789, 123456789, NULL);

INSERT INTO tb_aluno (ra, nome, email, data_nasc, idade, telefone, transporte, projeto_vida, serie, dado_familia_id, endereco_id) VALUES (123456789, 'Leandro Mendes', '123456789@exemplo.com', '2011-05-01', 14, 987654321, 'A pé', 'Ser médico', '8 ano A', 1, 1);
INSERT INTO tb_aluno (ra, nome, email, data_nasc, idade, telefone, transporte, projeto_vida, serie, dado_familia_id, endereco_id) VALUES (234567890, 'Sara Alvez', '234567890@exemplo.com', '2012-05-01', 13, 012345678, 'De carro', 'Ser juíza', '7 ano B', 2, 2);
INSERT INTO tb_aluno (ra, nome, email, data_nasc, idade, telefone, transporte, projeto_vida, serie, dado_familia_id, endereco_id) VALUES (987654321, 'João de Souza', '987654321@exemplo.com', '2013-06-23', 12, 321654987, 'A pé', 'Ser programador', '6 ano C', 3, 3);
INSERT INTO tb_aluno (ra, nome, email, data_nasc, idade, telefone, transporte, projeto_vida, serie, dado_familia_id, endereco_id) VALUES (543219876, 'Gustavo Lima', '543219876@exemplo.com', '2010-02-01', 15, 432198765, 'De bicicleta', 'Ser cantor', '9 ano D', 2, 2);

