
--CREATE TABLE users
--(
--    username varchar(50) PRIMARY KEY,
--    password varchar(500) NOT NULL,
--    enabled BOOLEAN NOT NULL
--);

CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    username varchar(50) UNIQUE NOT NULL,
    password varchar(500) NOT NULL,
    role varchar(50) NOT NULL
);

INSERT INTO users (id, username, password, role)
VALUES(1, 'kien', 'kien', 'admin')

CREATE TABLE authorities
(
    id SERIAL PRIMARY KEY,
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE
);

CREATE TABLE accounts
(
    id SERIAL PRIMARY KEY,
    uId SERIAL,
    acc varchar(50) NOT NULL,
    pass varchar(500) NOT NULL,
    web varchar(500) NOT NULL,
    CONSTRAINT fk_uId FOREIGN KEY (uId) REFERENCES users (id) ON DELETE CASCADE
);
