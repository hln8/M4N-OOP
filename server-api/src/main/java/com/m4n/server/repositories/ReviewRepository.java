package com.m4n.server.repositories;

import com.m4n.server.models.Movie;
import com.m4n.server.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMovie(Movie movie);
}
