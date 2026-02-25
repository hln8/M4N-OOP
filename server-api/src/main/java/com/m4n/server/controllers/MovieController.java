package com.m4n.server.controllers;

import com.m4n.server.models.Genre;
import com.m4n.server.models.Movie;
import com.m4n.server.services.MovieService;
import com.m4n.server.services.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final RatingService ratingService;

    public MovieController(MovieService movieService, RatingService ratingService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Long id) {
        return movieService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Movie create(@RequestBody Movie movie) {
        return movieService.create(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Movie> search(@RequestParam("q") String query) {
        return movieService.searchByTitle(query);
    }

    @GetMapping("/genre/{genre}")
    public List<Movie> byGenre(@PathVariable Genre genre) {
        return movieService.findByGenre(genre);
    }

    @GetMapping("/{id}/rating/average")
    public double getAverageRating(@PathVariable Long id) {
        return ratingService.getAverageRating(id);
    }
}

package com.m4n.server.controllers;

public class MovieController {
}
