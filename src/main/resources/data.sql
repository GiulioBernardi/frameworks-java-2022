insert into carro (placa, marca, modelo, cor, ano, status, quilometragem, diaria) values ('ABC-1234', 'Hyundai', 'HB20', 'Prata', 2018, 0, 43400, 95.50);
insert into cliente (nome, cpf, email, status, celular) values ('Luis Paulo Supimpa', 39371890017, 'luis_paulo@gmail.com', 0, 951789834);
insert into endereco (rua, numero, complemento, bairro, cidade, status, estado, cliente_key) values ('Rua Borboletas', 123, 'Ao lado da padaria', 'Centro', 'Santo André', 0, 'SP', 1);
insert into vendedor (nome, cpf, status, data_admissao) values ('Rodrigo Limeira Cachias', 34378748819, 0, '2015-04-18');
insert into conta_corrente (banco, agencia, conta_corrente, status, vendedor_key) values ('SANTANDER', 133, 2343, 0, 1);


insert into carro (placa, marca, modelo, cor, ano, status, quilometragem, diaria) values ('EFD-6958', 'Ford', 'Ka', 'Azul', 2019, 0, 63450, 56.50);
insert into cliente (nome, cpf, email, status, celular) values ('Giulio Cesar Bernardi', 45698732165, 'giulio@gmail.com', 0, 965214014);
insert into endereco (rua, numero, complemento, bairro, cidade, status, estado, cliente_key) values ('Rua Catalao', 155, 'Na esquina da igreja', 'Vila', 'Sao Paulo', 0, 'SP', 2);
insert into vendedor (nome, cpf, status, data_admissao) values ('Carlos da Silva Junior', 63625148798, 1, '2020-05-20');
insert into conta_corrente (banco, agencia, conta_corrente, status, vendedor_key) values ('Itau', 362, 2514, 0, 2);

