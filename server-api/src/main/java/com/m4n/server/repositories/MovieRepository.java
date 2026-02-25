package com.m4n.server.repositories;

import com.m4n.server.models.Genre;
import com.m4n.server.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContainingIgnoreCase(String titlePart);

    List<Movie> findByGenre(Genre genre);
}
