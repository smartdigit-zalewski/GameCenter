CREATE TABLE player (

    id BIGINT PRIMARY KEY,
    username VARCHAR(128) NOT NULL UNIQUE,
    email VARCHAR(128) UNIQUE,
    total_score INT,
    games_played INT
);

CREATE TABLE game (

    id BIGINT PRIMARY KEY,
    game_type VARCHAR(256),
    game_status VARCHAR(256),
    player_one_id BIGINT,
    player_two_id BIGINT,
    created TIMESTAMP,
    CONSTRAINT fk_game_player_one FOREIGN KEY (player_one_id) REFERENCES player(id),
    CONSTRAINT fk_game_player_two FOREIGN KEY (player_two_id) REFERENCES player(id)
);

