package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Auth0User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.User;
import com.qulificationRecomendation.qulificationRecomendation.Repo.Auth0UserRepository;
import com.qulificationRecomendation.qulificationRecomendation.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final Auth0UserRepository auth0UserRepository;
    @Autowired
    private UserRepository userRepository;

    public UserService(Auth0UserRepository auth0UserRepository) {
        this.auth0UserRepository = auth0UserRepository;
    }

    public Auth0User saveAuth0User(Auth0User auth0User) {
        return auth0UserRepository.save(auth0User);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
