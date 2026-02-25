package com.m4n.server.controllers;

import com.m4n.server.models.Review;
import com.m4n.server.models.User;
import com.m4n.server.services.ReviewService;
import com.m4n.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

record ReviewRequest(String content) {}

@RestController
@RequestMapping("/api/movies/{movieId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping
    public List<Review> list(@PathVariable Long movieId) {
        return reviewService.getReviewsForMovie(movieId);
    }

    @PostMapping
    public ResponseEntity<Review> add(@PathVariable Long movieId,
                                      @RequestBody ReviewRequest request,
                                      @AuthenticationPrincipal UserDetails principal) {
        User user = userService.findByUsername(principal.getUsername())
                .orElseThrow();
        Review review = reviewService.addReview(movieId, user, request.content());
        return ResponseEntity.ok(review);
    }
}

package com.m4n.server.controllers;

public class ReviewController {
}
