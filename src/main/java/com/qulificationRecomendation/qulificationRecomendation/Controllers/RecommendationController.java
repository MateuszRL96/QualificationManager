package com.qulificationRecomendation.qulificationRecomendation.Controllers;


import com.qulificationRecomendation.qulificationRecomendation.Entity.Recommendation;
import com.qulificationRecomendation.qulificationRecomendation.Entity.UserQualificationDetails;
import com.qulificationRecomendation.qulificationRecomendation.Services.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController { // -> localhost:8080/recommendations/user/1
    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/qualification/{qualificationId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsByQualification(@PathVariable Long qualificationId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByQualificationId(qualificationId));
    }

    @GetMapping
    public ResponseEntity<List<Recommendation>> getAllRecommendations() {
        List<Recommendation> recommendations = recommendationService.getAllRecommendations();
        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/user/1")
    public ResponseEntity<List<Object[]>> getQualificationsByUserId() {
        Long userId = 1L; // Hardcoded user_id
        List<Object[]> qualifications = recommendationService.getQualificationsByUserId(userId);
        return ResponseEntity.ok(qualifications);
    }
}
