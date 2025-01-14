package com.qulificationRecomendation.qulificationRecomendation.Repo;

import com.qulificationRecomendation.qulificationRecomendation.Entity.UserDetails;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserQualificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserQualificationDetailsRepository extends JpaRepository<UserQualificationDetails, Long> {
    Optional<UserQualificationDetails> findByUserDetailsAndQualificationId(UserDetails userDetails, Long qualificationId);
}
