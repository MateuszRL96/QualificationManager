package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Auth0User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.Qualification;
import com.qulificationRecomendation.qulificationRecomendation.Repo.Auth0UserRepository;
import com.qulificationRecomendation.qulificationRecomendation.Repo.QualificationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QualificationService {
    private final QualificationRepository qualificationRepository;
    private final Auth0UserRepository auth0UserRepository;

    public QualificationService(QualificationRepository qualificationRepository, Auth0UserRepository auth0UserRepository) {
        this.qualificationRepository = qualificationRepository;
        this.auth0UserRepository = auth0UserRepository;
    }

    @Transactional
    public Qualification addQualification(Qualification qualification) {
        return qualificationRepository.save(qualification);
    }

    public Qualification addQualification(Qualification qualification, @AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");

        if (email != null) {
            Optional<Auth0User> userOptional = auth0UserRepository.findTopByEmailOrderByIdDesc(email);
            if (userOptional.isPresent()) {
                Auth0User user = userOptional.get();
                qualification.setAuth0User(user);
                return qualificationRepository.save(qualification);
            }
        }
        return null;
    }

    public List<Qualification> getAllQualifications() {
        return qualificationRepository.findAll();
    }

    public void deleteQualification(Long id) {
        qualificationRepository.deleteById(id);
    }

    public Optional<Qualification> getQualificationWithSkills(Long id) {
        return qualificationRepository.findById(id);
    }

    public List<Qualification> getQualificationsByIds(List<Long> ids) {
        return ids.stream()
                .map(qualificationRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
    public Optional<Auth0User> getUserWithQualifications(String email) {
        return auth0UserRepository.findTopByEmailOrderByIdDesc(email)
                .map(user -> {
                    user.setQualifications(qualificationRepository.findByAuth0User(user));
                    return user;
                });
    }

    public void printUserWithQualifications(String email) {
        Optional<Auth0User> userOptional = getUserWithQualifications(email);
        if (userOptional.isPresent()) {
            Auth0User user = userOptional.get();
            System.out.println("User: " + user.getEmail());
            System.out.println("Qualifications:");
            for (Qualification qualification : user.getQualifications()) {
                System.out.println(" - " + qualification.getName() + ": " + qualification.getDescription());
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public List<Long> findQualificationIdsByName(String name) {
        return qualificationRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(Qualification::getId)
                .collect(Collectors.toList());
    }

    public List<String[]> getQualificationsByProfession(String profession) {
        List<String[]> qualifications = new ArrayList<>();
        String line;
        String csvFile = "src/main/java/com/qulificationRecomendation/qulificationRecomendation/DL4J/Data/dataset.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equalsIgnoreCase(profession)) {
                    qualifications.add(new String[]{values[1], values[2]});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return qualifications;
    }
}

