package com.m4n.server.controllers;

import com.m4n.server.models.User;
import com.m4n.server.services.RatingService;
import com.m4n.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

record RatingRequest(int score) {}

@RestController
@RequestMapping("/api/movies/{movieId}/rating")
public class RatingController {

    private final RatingService ratingService;
    private final UserService userService;

    public RatingController(RatingService ratingService, UserService userService) {
        this.ratingService = ratingService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> rateMovie(@PathVariable Long movieId,
                                       @RequestBody RatingRequest request,
                                       @AuthenticationPrincipal UserDetails principal) {
        User user = userService.findByUsername(principal.getUsername())
                .orElseThrow();
        return ResponseEntity.ok(ratingService.rateMovie(movieId, user, request.score()));
    }
}

package com.m4n.server.controllers;

public class RatingController {
}
