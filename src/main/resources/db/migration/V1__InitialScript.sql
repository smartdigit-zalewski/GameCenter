CREATE TABLE users (
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(50) NOT NULL,
                       enabled INT NOT NULL DEFAULT 1

);

INSERT INTO users (username, password, enabled) VALUES
('admin','{noop}1234',1 ),
('user','{noop}1234',1);



CREATE TABLE authorities (
                             username VARCHAR(50),
                             authority VARCHAR(50),
                             CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username),
                             UNIQUE (username,authority)

);

INSERT INTO authorities (username, authority) VALUES ('admin','ROLE_ADMIN'),
                                                     ('admin','ROLE_USER'),
                                                     ('user','ROLE_USER');

CREATE TABLE player (

                        id BIGSERIAL UNIQUE,
                        username VARCHAR(128) NOT NULL UNIQUE,
                        email VARCHAR(128) UNIQUE,
                        total_score INT,
                        games_played INT
);

INSERT INTO player (username, email, total_score, games_played) VALUES ('admin', 'admin@abc.pl',0,0), ('user', 'user@abc.pl',0,0);

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