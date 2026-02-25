CREATE DATABASE IF NOT EXISTS m4n_oop
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE m4n_oop;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS movies (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    release_date DATE,
    runtime_minutes INT,
    genre VARCHAR(50) NOT NULL,
    poster_url VARCHAR(500),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS ratings (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    score INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT chk_ratings_score CHECK (score BETWEEN 1 AND 10),
    CONSTRAINT fk_ratings_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_ratings_movie
        FOREIGN KEY (movie_id) REFERENCES movies(id)
        ON DELETE CASCADE,
    CONSTRAINT uq_ratings_user_movie UNIQUE (user_id, movie_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS reviews (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_reviews_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_reviews_movie
        FOREIGN KEY (movie_id) REFERENCES movies(id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS favorites (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_favorites_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_favorites_movie
        FOREIGN KEY (movie_id) REFERENCES movies(id)
        ON DELETE CASCADE,
    CONSTRAINT uq_favorites_user_movie UNIQUE (user_id, movie_id)
) ENGINE=InnoDB;

