package com.qulificationRecomendation.qulificationRecomendation.Repo;

import com.qulificationRecomendation.qulificationRecomendation.Entity.EmployerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerDetailsRepository extends JpaRepository<EmployerDetails, Long> {
}
