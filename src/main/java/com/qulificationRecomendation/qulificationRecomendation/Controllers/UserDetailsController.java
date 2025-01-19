package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Entity.User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserDetails;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserDetailsService;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-details")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDetails>> getAllUserDetails() {
        List<UserDetails> userDetailsList = userDetailsService.getAllUserDetails();
        return ResponseEntity.ok(userDetailsList);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUserDetails(@RequestBody UserDetails userDetails) {
        if (userDetailsService.existsByEmail(userDetails.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists");
        }
        userDetailsService.saveUserDetails(userDetails);
        return ResponseEntity.ok("User details added successfully");
    }
}