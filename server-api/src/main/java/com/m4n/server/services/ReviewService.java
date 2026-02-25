package com.m4n.server.services;

import com.m4n.server.models.Movie;
import com.m4n.server.models.Review;
import com.m4n.server.models.User;
import com.m4n.server.repositories.MovieRepository;
import com.m4n.server.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    public Review addReview(Long movieId, User user, String content) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + movieId));

        Review review = new Review(user, movie, content);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsForMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + movieId));
        return reviewRepository.findByMovie(movie);
    }
}
