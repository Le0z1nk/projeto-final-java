CREATE TABLE tb_colaboradores(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    cpf VARCHAR(15) UNIQUE NOT NULL,
    cargo INT 
);