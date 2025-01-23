package com.qulificationRecomendation.qulificationRecomendation.Repo;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Auth0User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    List<Qualification> findByAuth0User(Auth0User user);
    List<Qualification> findByNameContainingIgnoreCase(String name);
}