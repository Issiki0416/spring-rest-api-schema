CREATE TABLE samples (
                         id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         content VARCHAR(256) NOT NULL
);

CREATE TABLE tasks (
                       id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(256) NOT NULL
)