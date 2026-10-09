-- baza si tabela pentru AccountTest
-- local: mysql -u root < src/test/resources/createTables.sql
CREATE DATABASE IF NOT EXISTS exam_database;
USE exam_database;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
