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

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public void createUserIfNotExists(String name, String email) {
        Optional<UserDetails> existingUser = userDetailsRepository.findByEmail(email);

        if (!existingUser.isPresent()) {
            String[] nameParts = name.split(" ", 2);
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            UserDetails newUser = new UserDetails();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setPassword("defaultPassword"); // Set a default password or handle it appropriately
            newUser.setAge(0); // Set a default age or handle it appropriately
            newUser.setAddress("defaultAddress"); // Set a default address or handle it appropriately

            userDetailsRepository.save(newUser);
        }
    }

    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    public UserDetails saveUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    public boolean existsByEmail(String email) {
        return userDetailsRepository.existsByEmail(email);
    }
}