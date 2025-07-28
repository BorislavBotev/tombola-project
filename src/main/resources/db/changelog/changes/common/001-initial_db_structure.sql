-- liquibase formatted sql

--changeset [TBL-01]:20250728-001
CREATE TABLE players
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    email  VARCHAR(255) NOT NULL,
    name   VARCHAR(255) NOT NULL,
    uuid   VARCHAR(36)  NOT NULL,
    weight INT          NOT NULL CHECK (weight >= 0),
    UNIQUE (email),
    UNIQUE (uuid),
    CONSTRAINT PK_PLAYER PRIMARY KEY (id)
);

--changeset [TBL-01]:20250728-002
CREATE TABLE tombolas
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(255) NOT NULL,
    uuid        VARCHAR(36)  NOT NULL,
    max_players INT          NOT NULL CHECK (max_players > 0),
    max_awards  INT          NOT NULL CHECK (max_awards > 0),
    game_state  ENUM('WAITING', 'ACTIVE', 'COMPLETED') NOT NULL DEFAULT 'WAITING',
    UNIQUE (uuid),
    CONSTRAINT PK_TOMBOLA PRIMARY KEY (id)
);

--changeset [TBL-01]:20250728-003
CREATE TABLE awards
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255) NOT NULL,
    uuid       VARCHAR(36)  NOT NULL,
    player_id  BIGINT NULL,
    tombola_id BIGINT NULL,
    UNIQUE (uuid),
    CONSTRAINT PK_AWARD PRIMARY KEY (id),
    CONSTRAINT FK_aw_pl FOREIGN KEY (player_id)
        REFERENCES players (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT FK_aw_tbl FOREIGN KEY (tombola_id)
        REFERENCES tombolas (id) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--changeset [TBL-01]:20250728-004
CREATE TABLE tombolas_players
(
    tombola_id BIGINT NOT NULL,
    player_id  BIGINT NOT NULL,
    PRIMARY KEY (tombola_id, player_id),
    CONSTRAINT FK_tbls_pls_tbl FOREIGN KEY (tombola_id)
        REFERENCES tombolas (id)
        ON DELETE CASCADE,
    CONSTRAINT FK_tbls_pls_pl FOREIGN KEY (player_id)
        REFERENCES players (id)
        ON DELETE CASCADE
);