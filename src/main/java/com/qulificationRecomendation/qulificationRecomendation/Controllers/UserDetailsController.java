package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Entity.User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserDetails;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserDetailsService;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-details") //-> localhost:8080/api/user-details/all
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserDetails> getAllUserDetails() {
        return userDetailsService.getAllUserDetails();
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