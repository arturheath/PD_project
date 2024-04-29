CREATE TABLE movies
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    year        INTEGER,
    description VARCHAR(2550),
    banner      VARCHAR(255),
    category VARCHAR(100)
);

CREATE TABLE persons
(
    id         UUID PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    photo      VARCHAR(255),
    role       VARCHAR(255),
    birthday   DATE
);

CREATE TABLE movie_person
(
    person_id UUID,
    movie_id     UUID,
    PRIMARY KEY (person_id, movie_id),
    FOREIGN KEY (person_id) REFERENCES persons (id),
    FOREIGN KEY (movie_id) REFERENCES movies (id)
);
