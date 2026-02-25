package com.m4n.server.repositories;

import com.m4n.server.models.Movie;
import com.m4n.server.models.Rating;
import com.m4n.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByMovie(Movie movie);

    Optional<Rating> findByUserAndMovie(User user, Movie movie);
}
