CREATE TABLE users
(
    id     BIGSERIAL NOT NULL UNIQUE,
    name    VARCHAR   NOT NULL UNIQUE,
    password    VARCHAR   NOT NULL,
    role    VARCHAR   NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

INSERT INTO users (name, password, role)
VALUES ('miks', '234', 'ADMIN');
INSERT INTO users (name, password, role)
VALUES ('donis', 'lsmmb', 'ADMIN');
INSERT INTO users (name, password, role)
VALUES ('nsmr234', 'ezxid2', 'ADMIN');