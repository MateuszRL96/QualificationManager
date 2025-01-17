package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Entity.User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserDetails;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserDetailsService;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserDetailsController {

    @Autowired
    private UserService userService;

//    @GetMapping("/login")
//    public ResponseEntity<User> login(@AuthenticationPrincipal OAuth2User principal) {
//        String name = principal.getAttribute("name");
//        String email = principal.getAttribute("email");
//
//        User user = new User();
//        user.setName(name);
//        user.setEmail(email);
//
//        User savedUser = userService.saveUser(user);
//        return ResponseEntity.ok(savedUser);
//    }
}