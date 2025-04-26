CREATE TABLE petclub.servicos(
		ID INT(10) PRIMARY KEY AUTO_INCREMENT, 
        DESCRICAO VARCHAR(100), 
        PRECO FLOAT 
	);
CREATE TABLE petclub.clientes(
		ID INT(10) PRIMARY KEY AUTO_INCREMENT,
        NOME VARCHAR(100), 
        ENDERECO VARCHAR(100), 
        TELEFONE INT(20), 
        SALDO FLOAT 
	);
CREATE TABLE petclub.pets(
		ID INT(10) PRIMARY KEY AUTO_INCREMENT, 
        ID_CLIENTE INT(10), 
        NOME VARCHAR(100), 
        IDADE INT(10),
        RACA VARCHAR(50),
        PESO double,
        CONSTRAINT fkID_cliente FOREIGN KEY(ID_CLIENTE) REFERENCES clientes(ID)
	);
    
INSERT INTO petclub.servicos values (1, "banho", 100.00);
INSERT INTO petclub.servicos values (2, "banho e tosa", 150.00);
INSERT INTO petclub.servicos values (3, "vacina comum", 95.50);
INSERT INTO petclub.servicos values (4, "castracao", 178.50);
INSERT INTO petclub.servicos values (5, "racao 3kg", 67.99);
INSERT INTO petclub.servicos values (6, "racao 5kg", 89.00);

