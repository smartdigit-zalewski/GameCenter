CREATE TABLE users (
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(50) NOT NULL,
                       enabled INT NOT NULL DEFAULT 1

);


CREATE TABLE authorities (
                             username VARCHAR(50),
                             authority VARCHAR(50),
                             CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username),
                             UNIQUE (username,authority)

);


CREATE TABLE player (

                        id BIGSERIAL UNIQUE,
                        username VARCHAR(128) NOT NULL UNIQUE,
                        email VARCHAR(128) UNIQUE,
                        total_score INT,
                        games_played INT
);



CREATE TABLE game (

                      id BIGSERIAL UNIQUE,
                      game_type VARCHAR(256),
                      game_status VARCHAR(256),
                      player_one_id BIGINT,
                      player_two_id BIGINT,
                      created TIMESTAMP,
                      fleets VARCHAR(2048),
                      turn VARCHAR(64),
                      CONSTRAINT fk_game_player_one FOREIGN KEY (player_one_id) REFERENCES player(id),
                      CONSTRAINT fk_game_player_two FOREIGN KEY (player_two_id) REFERENCES player(id)
);