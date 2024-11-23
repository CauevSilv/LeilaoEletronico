CREATE TABLE Leilao
(
    id_leilao       INT AUTO_INCREMENT PRIMARY KEY,
    data_ocorrencia DATE         NOT NULL,
    data_visitacao  DATE         NOT NULL,
    local           VARCHAR(255) NOT NULL,
    endereco        VARCHAR(255),
    cidade          VARCHAR(100),
    estado          VARCHAR(2),
    status          VARCHAR(20)  NOT NULL
);

CREATE TABLE Lote
(
    id_lote       INT AUTO_INCREMENT PRIMARY KEY,
    tipo          VARCHAR(50)    NOT NULL,
    nome          VARCHAR(100)   NOT NULL,
    descricao     TEXT,
    lance_inicial DECIMAL(10, 2) NOT NULL,
    id_leilao     INT            NOT NULL,
    CONSTRAINT fk_leilao FOREIGN KEY (id_leilao) REFERENCES Leilao (id_leilao)
);

CREATE TABLE Cliente
(
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome       VARCHAR(100)       NOT NULL,
    cpf        VARCHAR(11) UNIQUE NOT NULL,
    email      VARCHAR(100),
    telefone   VARCHAR(15)
);

CREATE TABLE Lance
(
    id_lance   INT AUTO_INCREMENT PRIMARY KEY,
    valor      DECIMAL(10, 2) NOT NULL,
    id_cliente INT            NOT NULL,
    id_lote    INT            NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente),
    CONSTRAINT fk_lote FOREIGN KEY (id_lote) REFERENCES Lote (id_lote)
);

CREATE TABLE InstituicaoFinanceira
(
    id_instituicao INT AUTO_INCREMENT PRIMARY KEY,
    nome           VARCHAR(100) NOT NULL,
    cnpj           VARCHAR(14)  NOT NULL
);

CREATE TABLE Leilao_Instituicao
(
    id_leilao      INT,
    id_instituicao INT,
    PRIMARY KEY (id_leilao, id_instituicao),
    CONSTRAINT fk_leilao_inst FOREIGN KEY (id_leilao) REFERENCES Leilao (id_leilao),
    CONSTRAINT fk_inst_leilao FOREIGN KEY (id_instituicao) REFERENCES InstituicaoFinanceira (id_instituicao)
);


INSERT INTO Leilao (data_ocorrencia, data_visitacao, local, endereco, cidade, estado, status)
VALUES ('2024-11-30', '2024-11-29', 'Centro de Leilões', 'Rua Cleber, 1050', 'Alumínio', 'SP', 'EM ABERTO'),
    ('2024-10-30', '2024-10-28', 'Centro de Leilões', 'Rua Raiz, 20', 'Batatais', 'SP', 'EM ABERTO'),
    ('2024-09-30', '2024-09-28', 'Centro de Leilões', 'Rua Calvo de cria, 90', 'Descalvado', 'SP', 'EM ABERTO'),
    ('2024-12-10', '2024-12-08', 'Casa de Leilões', 'Av. Paulista, 1500', 'São Paulo', 'SP', 'EM ABERTO'),
    ('2024-11-15', '2024-11-13', 'Galpão Leiloeiro', 'Rua das Flores, 200', 'Campinas', 'SP', 'FINALIZADO'),
    ('2024-10-20', '2024-10-18', 'Espaço de Leilões', 'Rua das Palmeiras, 45', 'Ribeirão Preto', 'SP', 'CANCELADO'),
    ('2024-12-22', '2024-12-20', 'Arena de Leilões', 'Av. Independência, 3000', 'Sorocaba', 'SP', 'EM ABERTO');


INSERT INTO Lote (tipo, nome, descricao, lance_inicial, id_leilao)
VALUES ('dispositivo', 'Notebook Lenovo Thinkpad E14 Gen2', 'Notebook novo, 16GB RAM, SSD 512GB', 2500.00, 1),
       ('dispositivo', 'Smartphone Samsung', 'Modelo Xiaomi Mi 11, 256GB, novo', 3000.00, 1),
       ('dispositivo', 'Tablet Apple iPad', 'iPad Air, 64GB, perfeito estado', 1200.00, 2),
       ('dispositivo', 'Notebook HP', 'Notebook usado, 8GB RAM, SSD 256GB', 900.00, 3);

INSERT INTO Lote (tipo, nome, descricao, lance_inicial, id_leilao)
VALUES ('veículo', 'Carro Honda Civic', 'Carro apreendido, ano 2018, cor preta', 30000.00, 1),
       ('veículo', 'Moto Yamaha Fazer', 'Ano 2020, cor azul', 7000.00, 2),
       ('veículo', 'Caminhão Mercedes', 'Ano 2017, cor branca, em bom estado', 50000.00, 3),
       ('veículo', 'Carro Toyota Corolla', 'Ano 2019, cor prata', 45000.00, 4);


INSERT INTO Cliente (nome, cpf, email, telefone)
VALUES ('João Silva', '12345678901', 'joao@gmail.com', '11999999999'),
        ('Maria Oliveira', '23456789012', 'maria@gmail.com', '11988888888'),
        ('Carlos Souza', '34567890123', 'carlos@gmail.com', '11977777777'),
        ('Ana Lima', '45678901234', 'ana@gmail.com', '11966666666'),
        ('Pedro Costa', '56789012345', 'pedro@gmail.com', '11955555555');

INSERT INTO Lance (valor, id_cliente, id_lote)
VALUES (2600.00, 1, 1),
       (2800.00, 1, 1),
       (32000.00, 2, 2),
       (900.00, 3, 3),
       (7200.00, 4, 4),
       (51000.00, 1, 4),
       (46000.00, 2, 2);

INSERT INTO InstituicaoFinanceira (nome, cnpj)
VALUES ('Banco do Brasil', '12345678000199'),
       ('Caixa Econômica Federal', '23456789000188'),
       ('Bradesco', '34567890000177'),
       ('Itaú', '45678900000166'),
       ('Santander', '56789000000155');

INSERT INTO Leilao_Instituicao (id_leilao, id_instituicao)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4)
