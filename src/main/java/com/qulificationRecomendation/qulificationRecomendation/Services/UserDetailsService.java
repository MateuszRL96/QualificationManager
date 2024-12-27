package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.UserDetails;
import com.qulificationRecomendation.qulificationRecomendation.Repo.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserDetails saveUser(UserDetails userDetails) {
        // Manually encode the password if needed
        userDetails.setPassword(encodePassword(userDetails.getPassword()));
        return userDetailsRepository.save(userDetails);
    }

    public UserDetails findByEmail(String email) {
        return userDetailsRepository.findByEmail(email);
    }

    private String encodePassword(String password) {
        // Implement your own password encoding logic here
        return password; // Replace with actual encoding logic
    }
}