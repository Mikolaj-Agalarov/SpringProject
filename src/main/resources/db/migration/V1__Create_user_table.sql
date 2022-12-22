CREATE TABLE users
(
    id     BIGSERIAL NOT NULL UNIQUE,
    name    VARCHAR   NOT NULL UNIQUE,
    password    VARCHAR   NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

INSERT INTO users (name, password)
VALUES ('miks', '234');
INSERT INTO users (name, password)
VALUES ('donis', 'lsmmb');
INSERT INTO users (name, password)
VALUES ('nsmr234', 'ezxid2');