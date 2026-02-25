package com.m4n.server.services;

import com.m4n.server.models.Movie;
import com.m4n.server.models.Rating;
import com.m4n.server.models.User;
import com.m4n.server.repositories.MovieRepository;
import com.m4n.server.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    public RatingService(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    public Rating rateMovie(Long movieId, User user, int score) {
        if (score < 1 || score > 10) {
            throw new IllegalArgumentException("Score must be between 1 and 10");
        }

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + movieId));

        return ratingRepository.findByUserAndMovie(user, movie)
                .map(existing -> {
                    existing.setScore(score);
                    return ratingRepository.save(existing);
                })
                .orElseGet(() -> ratingRepository.save(new Rating(user, movie, score)));
    }

    public double getAverageRating(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + movieId));

        List<Rating> ratings = ratingRepository.findByMovie(movie);
        if (ratings.isEmpty()) {
            return 0.0;
        }
        DoubleSummaryStatistics stats = ratings.stream()
                .mapToDouble(Rating::getScore)
                .summaryStatistics();
        return stats.getAverage();
    }
}
