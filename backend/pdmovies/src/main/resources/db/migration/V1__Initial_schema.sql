CREATE TABLE movies
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    year        INTEGER,
    description VARCHAR(255),
    banner      VARCHAR(255),
    category VARCHAR(100)
);

CREATE TABLE celebrities
(
    id         UUID PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    photo      VARCHAR(255),
    role       VARCHAR(255),
    birthday   DATE
);

CREATE TABLE movie_celebrity
(
    celebrity_id UUID,
    movie_id     UUID,
    PRIMARY KEY (celebrity_id, movie_id),
    FOREIGN KEY (celebrity_id) REFERENCES celebrities (id),
    FOREIGN KEY (movie_id) REFERENCES movies (id)
);
