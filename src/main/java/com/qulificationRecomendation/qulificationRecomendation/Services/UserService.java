package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserDetails;
import com.qulificationRecomendation.qulificationRecomendation.Repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User registerUser(User user) {
        UserDetails userDetails = user.getUserDetails();
        userDetails.setPassword(hashPassword(userDetails.getPassword()));
        user.setUserDetails(userDetails);
        return userRepository.save(user);
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            UserDetails userDetails = user.getUserDetails();
            if (userDetails != null && checkPassword(password, userDetails.getPassword())) {
                return true;
            }
        }
        return false;
    }

    private String hashPassword(String password) {
        // Implement a simple hashing mechanism
        return Integer.toHexString(password.hashCode());
    }

    private boolean checkPassword(String rawPassword, String hashedPassword) {
        return hashedPassword.equals(Integer.toHexString(rawPassword.hashCode()));
    }
}