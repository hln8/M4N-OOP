package com.m4n.server.controllers;

import com.m4n.server.models.Favorite;
import com.m4n.server.models.User;
import com.m4n.server.services.FavoriteService;
import com.m4n.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UserService userService;

    public FavoriteController(FavoriteService favoriteService, UserService userService) {
        this.favoriteService = favoriteService;
        this.userService = userService;
    }

    @PostMapping("/movies/{movieId}/favorite")
    public ResponseEntity<?> addFavorite(@PathVariable Long movieId,
                                         @AuthenticationPrincipal UserDetails principal) {
        User user = userService.findByUsername(principal.getUsername())
                .orElseThrow();
        return ResponseEntity.ok(favoriteService.addFavorite(movieId, user));
    }

    @DeleteMapping("/movies/{movieId}/favorite")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long movieId,
                                               @AuthenticationPrincipal UserDetails principal) {
        User user = userService.findByUsername(principal.getUsername())
                .orElseThrow();
        favoriteService.removeFavorite(movieId, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/me/favorites")
    public List<Favorite> listFavorites(@AuthenticationPrincipal UserDetails principal) {
        User user = userService.findByUsername(principal.getUsername())
                .orElseThrow();
        return favoriteService.getFavorites(user);
    }
}

package com.m4n.server.controllers;

public class FavoriteController {
}
