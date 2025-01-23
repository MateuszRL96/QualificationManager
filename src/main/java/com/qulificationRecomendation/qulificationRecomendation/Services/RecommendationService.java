package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Recommendation;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserQualificationDetails;
import com.qulificationRecomendation.qulificationRecomendation.Repo.RecommendationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;

    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public List<Recommendation> getRecommendationsByQualificationId(Long qualificationId) {
        return recommendationRepository.findByQualificationId(qualificationId);
    }

    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    @Transactional
    public List<Object[]> getQualificationsByUserId(Long userId) {
        return recommendationRepository.findQualificationsByUserId(userId);
    }


}
