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


    private UserDetails getCurrentUser() {
        // Implement logic to retrieve the current user from the session
        return null;
    }
}