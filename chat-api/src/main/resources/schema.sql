-- Schema for the users table
CREATE TABLE IF NOT EXISTS users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       account_locked BOOLEAN NOT NULL DEFAULT FALSE,
                       enabled BOOLEAN NOT NULL DEFAULT TRUE
);

-- Schema for the messages table
CREATE TABLE IF NOT EXISTS messages (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          initiator VARCHAR(255) NOT NULL,
                          content TEXT NOT NULL,
                          type VARCHAR(50) NOT NULL,
                          date_time TIMESTAMP NOT NULL
);