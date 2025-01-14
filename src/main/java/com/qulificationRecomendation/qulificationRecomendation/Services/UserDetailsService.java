package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserDetails;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserQualificationDetails;
import com.qulificationRecomendation.qulificationRecomendation.Exceptions.UserNotFoundException;
import com.qulificationRecomendation.qulificationRecomendation.Repo.UserDetailsRepository;
import com.qulificationRecomendation.qulificationRecomendation.Repo.UserQualificationDetailsRepository;
import com.qulificationRecomendation.qulificationRecomendation.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserQualificationDetailsRepository userQualificationDetailsRepository;

    public UserDetails saveUser(UserDetails userDetails) {
        userDetails.setPassword(encodePassword(userDetails.getPassword()));
        return userDetailsRepository.save(userDetails);
    }

    public UserDetails findByEmail(String email) {
        return userDetailsRepository.findByEmail(email);
    }

    private String encodePassword(String password) {
        return password; // Replace with actual encoding logic
    }

    public UserDetails loginUser(String email, String password) {
        UserDetails userDetails = userDetailsRepository.findByEmail(email);
        if (userDetails != null && userDetails.getPassword().equals(password)) {
            return userDetails;
        }
        return null;
    }

    public void addOrUpdateQualification(Long qualificationId, int level) {
        // Retrieve user details from session
        UserDetails userDetails = getCurrentUser();
        Optional<UserQualificationDetails> existingQualification = userQualificationDetailsRepository.findByUserDetailsAndQualificationId(userDetails, qualificationId);
        UserQualificationDetails userQualificationDetails;
        if (existingQualification.isPresent()) {
            userQualificationDetails = existingQualification.get();
            userQualificationDetails.setLevel(level);
        } else {
            userQualificationDetails = new UserQualificationDetails();
            userQualificationDetails.setUserDetails(userDetails);
            userQualificationDetails.setId(qualificationId);
            userQualificationDetails.setLevel(level);
        }
        userQualificationDetailsRepository.save(userQualificationDetails);
    }

    public void deleteQualification(Long id) {
        userQualificationDetailsRepository.deleteById(id);
    }

    public User getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            // Handle the case where the user is not found
            // For example, throw an exception or return null
            throw new UserNotFoundException("User not found with email: " + email);
        }
    }

    private UserDetails getCurrentUser() {
        // Implement logic to retrieve the current user from the session
        return null;
    }
}