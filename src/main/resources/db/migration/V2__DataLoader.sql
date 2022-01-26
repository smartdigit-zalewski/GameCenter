INSERT INTO users (username, password, enabled) VALUES
('admin','{noop}1234',1 ),
('user','{noop}1234',1);


INSERT INTO authorities (username, authority) VALUES ('admin','ROLE_ADMIN'),
                                                     ('admin','ROLE_USER'),
                                                     ('user','ROLE_USER');

INSERT INTO player (username, email, total_score, games_played) VALUES ('admin', 'admin@abc.pl',0,0), ('user', 'user@abc.pl',0,0);