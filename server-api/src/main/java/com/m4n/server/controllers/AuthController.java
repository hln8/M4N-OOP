package com.m4n.server.controllers;

import com.m4n.server.models.User;
import com.m4n.server.services.AuthService;
import com.m4n.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

record RegisterRequest(String username, String email, String password) {}

record LoginRequest(String username, String password) {}

record AuthResponse(String token) {}

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User user = userService.register(request.username(), request.email(), request.password());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.username(), request.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

package com.m4n.server.controllers;

public class AuthController {
}
