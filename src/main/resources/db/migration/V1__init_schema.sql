-- create tables
CREATE TABLE groups
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE teams
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL UNIQUE,
    group_id INTEGER REFERENCES groups (id)
);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name     VARCHAR(255),
    role     VARCHAR(255)
);

CREATE TABLE phases
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255),
    phase_type   VARCHAR(255),
    phase_status VARCHAR(255)
);

CREATE TABLE matches
(
    id               SERIAL PRIMARY KEY,
    phase_id         INTEGER REFERENCES phases (id),
    team_left        INTEGER REFERENCES teams (id),
    team_right       INTEGER REFERENCES teams (id),
    team_left_score  INTEGER,
    team_right_score INTEGER,
    match_winner     INTEGER REFERENCES teams (id),
    match_date       TIMESTAMP,
    match_status     VARCHAR(255)
);

CREATE TABLE bets
(
    id                      SERIAL PRIMARY KEY,
    user_id                 INTEGER REFERENCES users (id),
    match_id                INTEGER REFERENCES matches (id),
    bet_left_score          INTEGER NOT NULL,
    bet_right_score         INTEGER NOT NULL,
    team_id                 INTEGER REFERENCES teams (id),
    points_for_bet          INTEGER,
    max_points_in_90_minutes BOOLEAN DEFAULT FALSE
);

CREATE TABLE phase_user_stats
(
    id                            SERIAL PRIMARY KEY,
    user_id                       INTEGER REFERENCES users (id),
    phase_id                      INTEGER REFERENCES phases (id),
    points                        INTEGER NOT NULL,
    bets_with_max_score           INTEGER NOT NULL,
    percentage_of_correct_guesses INTEGER NOT NULL,
    ranking_position              INTEGER NOT NULL
);

-- insert admin account
INSERT INTO users (id, login, password, name, role)
VALUES (1, 'admin', '$2a$10$Kw1PipVN4MbnRkG2Sc66FOSsPSsQIKcsfRSIhccFyRu6dfPFj6CGa', 'admin', 'ADMIN');