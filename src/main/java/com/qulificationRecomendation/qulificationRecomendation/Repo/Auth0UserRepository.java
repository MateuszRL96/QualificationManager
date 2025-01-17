package com.qulificationRecomendation.qulificationRecomendation.Repo;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Auth0User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Auth0UserRepository extends JpaRepository<Auth0User, Long> {
    boolean existsBySub(String sub);
}
