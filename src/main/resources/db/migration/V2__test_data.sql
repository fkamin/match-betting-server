-- insert groups
INSERT INTO groups (id, name)
VALUES (1, 'A'),
       (2, 'B'),
       (3, 'C'),
       (4, 'D');

-- insert teams
INSERT INTO teams (id, group_id, name)
VALUES (1, 1, 'Polska'),
       (2, 1, 'Niemcy'),
       (3, 1, 'Hiszpania'),
       (4, 1, 'Włochy'),
       (5, 2, 'Francja'),
       (6, 2, 'Anglia'),
       (7, 2, 'Holandia'),
       (8, 2, 'Belgia'),
       (9, 3, 'Brazylia'),
       (10, 3, 'Argentyna'),
       (11, 3, 'Urugwaj'),
       (12, 3, 'Chile'),
       (13, 4, 'USA'),
       (14, 4, 'Meksyk'),
       (15, 4, 'Kanada'),
       (16, 4, 'Kolumbia');

-- insert 6 users
INSERT INTO users (id, login, password, name, role)
VALUES (2, 'user1', '$2a$10$4s6ZyfYxIR5FF96n3q.4tOWJoJUO3YNoiZpLiy9AZKm.O7LEzNOsG', 'User One', 'USER'),
       (3, 'user2', '$2a$10$4s6ZyfYxIR5FF96n3q.4tOWJoJUO3YNoiZpLiy9AZKm.O7LEzNOsG', 'User Two', 'USER'),
       (4, 'user3', '$2a$10$4s6ZyfYxIR5FF96n3q.4tOWJoJUO3YNoiZpLiy9AZKm.O7LEzNOsG', 'User Three', 'USER'),
       (5, 'user4', '$2a$10$4s6ZyfYxIR5FF96n3q.4tOWJoJUO3YNoiZpLiy9AZKm.O7LEzNOsG', 'User Four', 'USER'),
       (6, 'user5', '$2a$10$4s6ZyfYxIR5FF96n3q.4tOWJoJUO3YNoiZpLiy9AZKm.O7LEzNOsG', 'User Five', 'USER'),
       (7, 'user6', '$2a$10$4s6ZyfYxIR5FF96n3q.4tOWJoJUO3YNoiZpLiy9AZKm.O7LEzNOsG', 'User Six', 'USER');

-- insert phase
INSERT INTO phases (id, name, phase_type, phase_status)
VALUES (1, 'Mundial 2026 - faza grupowa', 'GROUP_STAGE', 'GAMEPLAY');

-- assign users to phase
INSERT INTO phase_user_stats (id, user_id, phase_id, points, percentage_of_correct_guesses, ranking_position,
                              bets_with_max_score)
VALUES (1, 2, 1, 0, 0, 0, 0),
       (2, 3, 1, 0, 0, 0, 0),
       (3, 4, 1, 0, 0, 0, 0),
       (4, 5, 1, 0, 0, 0, 0),
       (5, 6, 1, 0, 0, 0, 0),
       (6, 7, 1, 0, 0, 0, 0);


-- insert matches
INSERT INTO matches (id, phase_id, team_left, team_right, match_date, match_status)
VALUES (1, 1, 1, 2, '2026-06-01 18:00:00', 'SCHEDULED'),
       (2, 1, 3, 4, '2026-06-01 18:00:00', 'SCHEDULED'),
       (3, 1, 1, 3, '2026-06-03 18:00:00', 'SCHEDULED'),
       (4, 1, 2, 4, '2026-06-04 18:00:00', 'SCHEDULED'),
       (5, 1, 4, 1, '2026-06-05 18:00:00', 'SCHEDULED'),
       (6, 1, 2, 3, '2026-06-06 18:00:00', 'SCHEDULED'),

       (7, 1, 5, 6, '2026-06-01 18:00:00', 'SCHEDULED'),
       (8, 1, 7, 8, '2026-06-01 18:00:00', 'SCHEDULED'),
       (9, 1, 5, 7, '2026-06-03 18:00:00', 'SCHEDULED'),
       (10, 1, 6, 8, '2026-06-04 18:00:00', 'SCHEDULED'),
       (11, 1, 8, 5, '2026-06-05 18:00:00', 'SCHEDULED'),
       (12, 1, 6, 7, '2026-06-06 18:00:00', 'SCHEDULED'),

       (13, 1, 9, 10, '2026-06-01 18:00:00', 'SCHEDULED'),
       (14, 1, 11, 12, '2026-06-01 18:00:00', 'SCHEDULED'),
       (15, 1, 9, 11, '2026-06-03 18:00:00', 'SCHEDULED'),
       (16, 1, 10, 12, '2026-06-04 18:00:00', 'SCHEDULED'),
       (17, 1, 12, 9, '2026-06-05 18:00:00', 'SCHEDULED'),
       (18, 1, 10, 11, '2026-06-06 18:00:00', 'SCHEDULED'),

       (19, 1, 13, 14, '2026-06-01 18:00:00', 'SCHEDULED'),
       (20, 1, 15, 16, '2026-06-01 18:00:00', 'SCHEDULED'),
       (21, 1, 13, 15, '2026-06-03 18:00:00', 'SCHEDULED'),
       (22, 1, 14, 16, '2026-06-04 18:00:00', 'SCHEDULED'),
       (23, 1, 16, 13, '2026-06-05 18:00:00', 'SCHEDULED'),
       (24, 1, 14, 15, '2026-06-06 18:00:00', 'SCHEDULED');

-- insert bets
-- Użytkownik 2
INSERT INTO bets (user_id, match_id, bet_left_score, bet_right_score, team_id)
VALUES (2, 1, 1, 0, null),
       (2, 2, 2, 1, null),
       (2, 3, 1, 1, null),
       (2, 4, 0, 2, null),
       (2, 5, 3, 2, null),
       (2, 6, 0, 0, null),
       (2, 7, 1, 2, null),
       (2, 8, 2, 2, null),
       (2, 9, 3, 1, null),
       (2, 10, 0, 1, null),
       (2, 11, 1, 1, null),
       (2, 12, 0, 3, null),
       (2, 13, 2, 0, null),
       (2, 14, 1, 2, null),
       (2, 15, 2, 2, null),
       (2, 16, 3, 0, null),
       (2, 17, 1, 1, null),
       (2, 18, 0, 2, null),
       (2, 19, 1, 0, null),
       (2, 20, 1, 1, null),
       (2, 21, 2, 1, null),
       (2, 22, 0, 0, null),
       (2, 23, 3, 1, null),
       (2, 24, 2, 2, null);

-- Użytkownik 3
INSERT INTO bets (user_id, match_id, bet_left_score, bet_right_score, team_id)
VALUES (3, 1, 2, 1, null),
       (3, 2, 1, 2, null),
       (3, 3, 0, 0, null),
       (3, 4, 3, 1, null),
       (3, 5, 2, 2, null),
       (3, 6, 1, 0, null),
       (3, 7, 1, 1, null),
       (3, 8, 2, 3, null),
       (3, 9, 2, 0, null),
       (3, 10, 1, 1, null),
       (3, 11, 0, 2, null),
       (3, 12, 2, 1, null),
       (3, 13, 1, 3, null),
       (3, 14, 2, 2, null),
       (3, 15, 1, 0, null),
       (3, 16, 0, 1, null),
       (3, 17, 2, 2, null),
       (3, 18, 3, 2, null),
       (3, 19, 0, 2, null),
       (3, 20, 1, 1, null),
       (3, 21, 2, 0, null),
       (3, 22, 1, 1, null),
       (3, 23, 2, 3, null),
       (3, 24, 0, 2, null);

-- Użytkownik 4
INSERT INTO bets (user_id, match_id, bet_left_score, bet_right_score, team_id)
VALUES (4, 1, 0, 1, null),
       (4, 2, 1, 1, null),
       (4, 3, 2, 1, null),
       (4, 4, 2, 2, null),
       (4, 5, 3, 1, null),
       (4, 6, 1, 0, null),
       (4, 7, 1, 1, null),
       (4, 8, 0, 2, null),
       (4, 9, 1, 3, null),
       (4, 10, 2, 0, null),
       (4, 11, 0, 0, null),
       (4, 12, 1, 1, null),
       (4, 13, 3, 2, null),
       (4, 14, 2, 2, null),
       (4, 15, 0, 1, null),
       (4, 16, 1, 0, null),
       (4, 17, 0, 0, null),
       (4, 18, 1, 3, null),
       (4, 19, 2, 2, null),
       (4, 20, 1, 0, null),
       (4, 21, 3, 2, null),
       (4, 22, 0, 1, null),
       (4, 23, 2, 2, null),
       (4, 24, 1, 1, null);

-- Użytkownik 5
INSERT INTO bets (user_id, match_id, bet_left_score, bet_right_score, team_id)
VALUES (5, 1, 1, 2, null),
       (5, 2, 2, 0, null),
       (5, 3, 1, 1, null),
       (5, 4, 1, 0, null),
       (5, 5, 2, 3, null),
       (5, 6, 0, 2, null),
       (5, 7, 2, 2, null),
       (5, 8, 1, 1, null),
       (5, 9, 2, 1, null),
       (5, 10, 1, 0, null),
       (5, 11, 0, 0, null),
       (5, 12, 2, 3, null),
       (5, 13, 1, 0, null),
       (5, 14, 0, 1, null),
       (5, 15, 2, 0, null),
       (5, 16, 2, 2, null),
       (5, 17, 1, 1, null),
       (5, 18, 3, 2, null),
       (5, 19, 1, 2, null),
       (5, 20, 2, 2, null),
       (5, 21, 1, 1, null),
       (5, 22, 0, 1, null),
       (5, 23, 2, 1, null),
       (5, 24, 3, 0, null);

-- Użytkownik 6
INSERT INTO bets (user_id, match_id, bet_left_score, bet_right_score, team_id)
VALUES (6, 1, 0, 0, null),
       (6, 2, 2, 1, null),
       (6, 3, 1, 0, null),
       (6, 4, 3, 2, null),
       (6, 5, 1, 3, null),
       (6, 6, 0, 1, null),
       (6, 7, 1, 0, null),
       (6, 8, 2, 2, null),
       (6, 9, 0, 2, null),
       (6, 10, 1, 2, null),
       (6, 11, 1, 1, null),
       (6, 12, 3, 1, null),
       (6, 13, 1, 2, null),
       (6, 14, 0, 3, null),
       (6, 15, 2, 2, null),
       (6, 16, 1, 0, null),
       (6, 17, 1, 1, null),
       (6, 18, 2, 1, null),
       (6, 19, 0, 0, null),
       (6, 20, 1, 1, null),
       (6, 21, 2, 2, null),
       (6, 22, 1, 0, null),
       (6, 23, 0, 2, null),
       (6, 24, 3, 1, null);

-- Użytkownik 7
INSERT INTO bets (user_id, match_id, bet_left_score, bet_right_score, team_id)
VALUES (7, 1, 1, 1, null),
       (7, 2, 3, 0, null),
       (7, 3, 2, 1, null),
       (7, 4, 0, 0, null),
       (7, 5, 2, 0, null),
       (7, 6, 1, 2, null),
       (7, 7, 3, 1, null),
       (7, 8, 0, 0, null),
       (7, 9, 1, 1, null),
       (7, 10, 2, 2, null),
       (7, 11, 0, 1, null),
       (7, 12, 1, 3, null),
       (7, 13, 2, 0, null),
       (7, 14, 1, 1, null),
       (7, 15, 0, 2, null),
       (7, 16, 2, 3, null),
       (7, 17, 2, 1, null),
       (7, 18, 1, 1, null),
       (7, 19, 3, 3, null),
       (7, 20, 0, 1, null),
       (7, 21, 1, 2, null),
       (7, 22, 1, 1, null),
       (7, 23, 2, 2, null),
       (7, 24, 0, 1, null);