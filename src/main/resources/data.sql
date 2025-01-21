-- Criação da tabela users
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Inserção de registros na tabela users
INSERT INTO users (name, email, password) VALUES
('Teste', 'teste@teste.com', '$2a$10$A6fhPelc3sLsTD02tb05FOOpeEfmWncQBPZ48v9M87qcb4AVWN106'),
('Teste2', 'teste2@teste.com', '$2a$10$tqdEs/5tj/D1Bkc4zThc8eUjRHOGvGPXDreBUSFZNBN2X6KE1xQPS'),
('Teste3', 'teste3@teste.com', '$2a$10$l948S1aBc0NJzRff7IF8ou7EaxHzdQ18u83JRnHpRETJ1.ocla0z6');
