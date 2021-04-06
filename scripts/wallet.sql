CREATE SCHEMA wallet_backend;
USE wallet_backend;

CREATE TABLE IF NOT EXISTS users
(
    id       CHAR(36)     NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    name     VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS accounts
(
    id      CHAR(36) NOT NULL,
    user_id CHAR(36) NOT NULL,
    balance FLOAT    NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS transactions
(
    id          CHAR(36)     NOT NULL,
    concept     VARCHAR(255) NULL,
    destination CHAR(36)     NOT NULL,
    origin      CHAR(36)     NOT NULL,
    quantity    FLOAT        NOT NULL,
    type        VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (destination) REFERENCES accounts (id),
    FOREIGN KEY (origin) REFERENCES accounts (id)
) ENGINE = InnoDB;