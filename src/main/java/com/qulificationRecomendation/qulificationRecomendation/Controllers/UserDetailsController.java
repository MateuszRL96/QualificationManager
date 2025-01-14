package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Entity.User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserDetails;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password) {
        UserDetails userDetails = userDetailsService.loginUser(email, password);
        if (userDetails != null) {
            // Store user details in session and redirect to user profile
            return "redirect:/profile";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/profile")
    public String showUserProfile() {
        // Retrieve user details from session and display profile
        return "profile";
    }

    @GetMapping("/profile/{email}")
    public String getUserProfile(@PathVariable String email, Model model) {
        User user = userDetailsService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/qualifications")
    public String addOrUpdateQualification(@RequestParam Long qualificationId, @RequestParam int level) {
        userDetailsService.addOrUpdateQualification(qualificationId, level);
        return "redirect:/profile";
    }

    @DeleteMapping("/profile/qualifications/{id}")
    public String deleteQualification(@PathVariable Long id) {
        userDetailsService.deleteQualification(id);
        return "redirect:/profile";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,
                               @RequestParam String password, @RequestParam int age, @RequestParam String address) {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        userDetails.setEmail(email);
        userDetails.setPassword(password);
        userDetails.setAge(age);
        userDetails.setAddress(address);
        userDetailsService.saveUser(userDetails);
        return "redirect:/login";
    }

    @PostMapping("/submitUserDetails")
    public String submitUserDetails(@RequestParam String email, @RequestParam String password, Model model) {
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(email);
        userDetails.setPassword(password);
        userDetailsService.saveUser(userDetails);

        // Retrieve the user details to pass to the profile page
        UserDetails savedUserDetails = userDetailsService.findByEmail(email);
        model.addAttribute("user", savedUserDetails);

        // Redirect to the profile page
        return "profile";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}