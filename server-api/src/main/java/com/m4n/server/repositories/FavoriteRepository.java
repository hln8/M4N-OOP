package com.m4n.server.repositories;

import com.m4n.server.models.Favorite;
import com.m4n.server.models.Movie;
import com.m4n.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUser(User user);

    Optional<Favorite> findByUserAndMovie(User user, Movie movie);
}
