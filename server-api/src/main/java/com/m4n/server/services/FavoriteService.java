package com.m4n.server.services;

import com.m4n.server.models.Favorite;
import com.m4n.server.models.Movie;
import com.m4n.server.models.User;
import com.m4n.server.repositories.FavoriteRepository;
import com.m4n.server.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final MovieRepository movieRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, MovieRepository movieRepository) {
        this.favoriteRepository = favoriteRepository;
        this.movieRepository = movieRepository;
    }

    public Favorite addFavorite(Long movieId, User user) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + movieId));

        return favoriteRepository.findByUserAndMovie(user, movie)
                .orElseGet(() -> favoriteRepository.save(new Favorite(user, movie)));
    }

    public void removeFavorite(Long movieId, User user) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + movieId));

        favoriteRepository.findByUserAndMovie(user, movie)
                .ifPresent(favoriteRepository::delete);
    }

    public List<Favorite> getFavorites(User user) {
        return favoriteRepository.findByUser(user);
    }
}
