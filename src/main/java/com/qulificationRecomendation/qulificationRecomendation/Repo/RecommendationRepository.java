package com.qulificationRecomendation.qulificationRecomendation.Repo;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Recommendation;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserQualificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByQualificationId(Long qualificationId);

    @Query(value = "SELECT q.name, uqd.level " +
            "FROM qualifications q " +
            "JOIN user_qualification_details uqd ON q.id = uqd.qualification_id " +
            "WHERE uqd.user_id = :userId", nativeQuery = true)
    List<Object[]> findQualificationsByUserId(@Param("userId") Long userId);
}

