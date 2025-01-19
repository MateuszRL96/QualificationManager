package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Auth0User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.Qualification;
import com.qulificationRecomendation.qulificationRecomendation.Repo.Auth0UserRepository;
import com.qulificationRecomendation.qulificationRecomendation.Repo.QualificationRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final Auth0UserRepository auth0UserRepository;
    private final QualificationRepository qualificationRepository;

    public HomeController(Auth0UserRepository auth0UserRepository, QualificationRepository qualificationRepository) {
        this.auth0UserRepository = auth0UserRepository;
        this.qualificationRepository = qualificationRepository;

    }

    @GetMapping("/user")
    public Auth0User getUser(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");

        if (email != null) {
            return auth0UserRepository.findTopByEmailOrderByIdDesc(email).orElse(null);
        } else {
            return null;
        }
    }

    @PostMapping("/user/qualifications")
    public Qualification addQualification(@AuthenticationPrincipal OAuth2User principal, @RequestBody Qualification qualification) {
        String email = principal.getAttribute("email");

        if (email != null) {
            Auth0User user = auth0UserRepository.findTopByEmailOrderByIdDesc(email).orElse(null);
            if (user != null) {
                qualification.setAuth0User(user);
                return qualificationRepository.save(qualification);
            }
        }
        return null;
    }
}