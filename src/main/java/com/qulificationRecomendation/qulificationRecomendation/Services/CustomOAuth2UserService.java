package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Auth0User;
import com.qulificationRecomendation.qulificationRecomendation.Repo.Auth0UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);
    private final Auth0UserRepository auth0UserRepository;

    public CustomOAuth2UserService(Auth0UserRepository auth0UserRepository) {
        this.auth0UserRepository = auth0UserRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        if (oAuth2User instanceof OidcUser) {
            OidcUser oidcUser = (OidcUser) oAuth2User;

            String sub = oidcUser.getSubject();
            String email = oidcUser.getEmail();
            String name = oidcUser.getFullName();
            String picture = oidcUser.getPicture();

            if (name == null) {
                name = "Default Name";
            }

            if (email == null) {
                email = "Default Email";
            }

            if (!auth0UserRepository.existsBySub(sub)) {
                Auth0User user = new Auth0User();
                user.setSub(sub);
                user.setEmail(email);
                user.setName(name);
                user.setPicture(picture);
                user.setLoginDate(LocalDateTime.now());

                auth0UserRepository.save(user);
            }

            // Log user details to the console
            logger.info("User logged in: sub={}, email={}, name={}, picture={}", sub, email, name, picture);
        }

        return oAuth2User;
    }
}