-- DROP TABLE IF EXISTS users;
-- DROP DATABASE IF EXISTS user_db;

CREATE DATABASE user_db;

CREATE TABLE IF NOT EXISTS users (
        user_id BIGSERIAL PRIMARY KEY,
        name VARCHAR(20) NOT NULL,
        email VARCHAR(20) UNIQUE NOT NULL,
        age INT NOT NULL,
        created_ad TIMESTAMP
);
