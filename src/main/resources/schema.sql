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
CREATE TABLE IF NOT EXISTS Dispositivo
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    descricao    VARCHAR(255),
    valor_inicial DECIMAL(15, 2),
    vendido      BOOLEAN,
    lote_id    BIGINT,
    nome         VARCHAR(255),
    tipo         VARCHAR(255),
    CONSTRAINT pk_dispositivo PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS Veiculo
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    descricao    VARCHAR(255),
    valor_inicial DECIMAL(15, 2),
    vendido      BOOLEAN,
    lote_id    BIGINT,
    modelo       VARCHAR(255),
    marca        VARCHAR(255),
    tipo         VARCHAR(255),
    CONSTRAINT pk_veiculo PRIMARY KEY (id)
);

CREATE TABLE Lote_tipo(
                          id int AUTO_INCREMENT PRIMARY KEY ,
                          id_lote int,
                          id_veiculo int,
                          id_dispositivo int,
                          CONSTRAINT pk_leilaotipo PRIMARY KEY (id),
                          CONSTRAINT fk_veiculo FOREIGN KEY (id_veiculo) REFERENCES Veiculo (id),
                          CONSTRAINT fk_dispositivo FOREIGN KEY (id_dispositivo) REFERENCES Dispositivo (id)
);

CREATE TABLE Lote
(
    id_lote       INT AUTO_INCREMENT PRIMARY KEY,
    nome varchar,
    descricao     TEXT,
    lance_inicial DECIMAL(10, 2) NOT NULL,
    id_leilao     INT            NOT NULL,
    id_lote_tipo   int,
    CONSTRAINT fk_leilao FOREIGN KEY (id_leilao) REFERENCES Leilao (id_leilao),
    CONSTRAINT fk_id_leilao_tipo FOREIGN KEY (id_lote_tipo) REFERENCES Lote_tipo (id)
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
CREATE VIEW lote_valor_total as
SELECT l.id_lote, l.nome, l.descricao,l.lance_inicial, l.lance_inicial + COALESCE(SUM(c.valor), 0) AS valor_total FROM lote l LEFT JOIN lance c ON l.id_lote = c.id_lote  GROUP BY l.id_lote;

INSERT INTO Leilao (data_ocorrencia, data_visitacao, local, endereco, cidade, estado, status)
VALUES
    ('2024-12-15', '2024-12-10', 'Espaço de Leilões', 'Rua Principal, 123', 'São Paulo', 'SP', 'EM ABERTO'),
    ('2024-12-20', '2024-12-18', 'Centro de Leilões', 'Av. Central, 456', 'Campinas', 'SP', 'EM ABERTO'),
    ('2024-11-25', '2024-11-22', 'Casa de Leilões', 'Rua das Flores, 789', 'Ribeirão Preto', 'SP', 'FINALIZADO');

INSERT INTO Veiculo (descricao, valor_inicial, vendido, modelo, marca, tipo, lote_id)
VALUES
    ('Honda Civic 2020, cor prata', 80000.00, FALSE, 'Civic', 'Honda', 'Carro', 1),
    ('Toyota Corolla 2019, cor preta', 75000.00, TRUE, 'Corolla', 'Toyota', 'Carro', 1),
    ('Ford Ranger 2021, cor azul', 120000.00, FALSE, 'Ranger', 'Ford', 'Caminhão', 2);

INSERT INTO Dispositivo (descricao, valor_inicial, vendido, nome, tipo, lote_id)
VALUES
    ('Notebook Dell Inspiron, 16GB RAM, SSD 512GB', 5000.00, FALSE, 'Notebook Dell Inspiron', 'Notebook', 3),
    ('Smartphone Samsung Galaxy S21, 256GB', 4000.00, TRUE, 'Samsung Galaxy S21', 'Smartphone', 3),
    ('Tablet Apple iPad Pro, 128GB', 6000.00, FALSE, 'Apple iPad Pro', 'Tablet', 3);

INSERT INTO Lote_tipo (id_lote, id_veiculo, id_dispositivo)
VALUES
    (1, 1, NULL),
    (2, 2, NULL),
    (3, NULL, 1),
    (3, NULL, 2);

INSERT INTO Lote (nome,descricao, lance_inicial, id_leilao, id_lote_tipo)
VALUES
    ('Carro ricos','Lote com veículos de luxo.', 70000.00, 1, 1),
    ('Carro pobres','Lote com carros populares.', 50000.00, 2, 2),
    ('Iphone nao comprar','Lote com dispositivos eletrônicos.', 3000.00, 3, 1);

INSERT INTO Cliente (nome, cpf, email, telefone)
VALUES
    ('João Silva', '12345678901', 'joao@gmail.com', '11999999999'),
    ('Maria Oliveira', '23456789012', 'maria@gmail.com', '11988888888'),
    ('Carlos Souza', '34567890123', 'carlos@gmail.com', '11977777777');

INSERT INTO Lance (valor, id_cliente, id_lote)
VALUES
    (75000.00, 1, 1),
    (3100.00, 2, 3),
    (51000.00, 3, 2);

INSERT INTO InstituicaoFinanceira (nome, cnpj)
VALUES
    ('Banco do Brasil', '12345678000199'),
    ('Itaú', '23456789000188'),
    ('Caixa Econômica Federal', '34567890000177');

INSERT INTO Leilao_Instituicao (id_leilao, id_instituicao)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);


