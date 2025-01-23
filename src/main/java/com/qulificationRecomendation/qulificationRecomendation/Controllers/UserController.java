package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Auth0User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserDetails;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserDetailsService;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@AuthenticationPrincipal OidcUser principal) {
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("email");
        String sub = principal.getAttribute("sub");
        String picture = principal.getAttribute("picture");
        String token = principal.getIdToken().getTokenValue(); // Pobierz token

        // Wypisz token w konsoli
        logger.info("Token: {}", token);

        // Create user in user_details if not exists
        userDetailsService.createUserIfNotExists(name, email);

        Auth0User auth0User = new Auth0User();
        auth0User.setName(name);
        auth0User.setEmail(email);
        auth0User.setSub(sub);
        auth0User.setPicture(picture);
        auth0User.setLoginDate(LocalDateTime.now());

        Auth0User savedAuth0User = userService.saveAuth0User(auth0User);

        // Przygotuj odpowiedź z tokenem
        Map<String, Object> response = new HashMap<>();
        response.put("user", savedAuth0User);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-profile")
    public ResponseEntity<UserDetails> createProfile(@RequestBody UserDetails userDetails) {
        UserDetails savedUserDetails = userDetailsService.saveUserDetails(userDetails);
        return ResponseEntity.ok(savedUserDetails);
    }
}