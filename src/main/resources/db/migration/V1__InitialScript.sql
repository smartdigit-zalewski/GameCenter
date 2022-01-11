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