CREATE TABLE friends
(
    id             BIGSERIAL NOT NULL UNIQUE,
    source_user_id BIGINT    NOT NULL,
    target_user_id BIGINT    NOT NULL,
    status         VARCHAR DEFAULT 'NEW',

    PRIMARY KEY (id)
);
INSERT INTO friends (source_user_id, target_user_id)
VALUES (1, 2);
