package com.m4n.server.services;

import com.m4n.server.models.Genre;
import com.m4n.server.models.Movie;
import com.m4n.server.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie create(Movie movie) {
        movie.setId(null);
        return movieRepository.save(movie);
    }

    public Movie update(Long id, Movie updated) {
        return movieRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updated.getTitle());
                    existing.setDescription(updated.getDescription());
                    existing.setReleaseDate(updated.getReleaseDate());
                    existing.setRuntimeMinutes(updated.getRuntimeMinutes());
                    existing.setGenre(updated.getGenre());
                    existing.setPosterUrl(updated.getPosterUrl());
                    return movieRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + id));
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> findByGenre(Genre genre) {
        return movieRepository.findByGenre(genre);
    }
}
