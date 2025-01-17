package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Auth0User;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<Auth0User> login(@AuthenticationPrincipal OAuth2User principal) {
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("email");
        String sub = principal.getAttribute("sub");
        String picture = principal.getAttribute("picture");

        Auth0User auth0User = new Auth0User();
        auth0User.setName(name);
        auth0User.setEmail(email);
        auth0User.setSub(sub);
        auth0User.setPicture(picture);
        auth0User.setLoginDate(LocalDateTime.now());

        Auth0User savedAuth0User = userService.saveAuth0User(auth0User);
        return ResponseEntity.ok(savedAuth0User);
    }
}
