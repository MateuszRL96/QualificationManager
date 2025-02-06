package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Recommendation;
import com.qulificationRecomendation.qulificationRecomendation.Repo.RecommendationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class RecommendationServiceTest {

    @Mock
    private RecommendationRepository recommendationRepository;

    @InjectMocks
    private RecommendationService recommendationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRecommendationsByQualificationIdReturnsRecommendations() {
        List<Recommendation> recommendations = Collections.singletonList(new Recommendation());
        when(recommendationRepository.findByQualificationId(anyLong())).thenReturn(recommendations);

        List<Recommendation> result = recommendationService.getRecommendationsByQualificationId(1L);

        assertEquals(recommendations, result);
    }

    @Test
    void getAllRecommendationsReturnsAllRecommendations() {
        List<Recommendation> recommendations = Collections.singletonList(new Recommendation());
        when(recommendationRepository.findAll()).thenReturn(recommendations);

        List<Recommendation> result = recommendationService.getAllRecommendations();

        assertEquals(recommendations, result);
    }

    @Test
    void getQualificationsByUserIdReturnsQualifications() {
        List<Object[]> qualifications = Collections.singletonList(new Object[]{"Qualification1"});
        when(recommendationRepository.findQualificationsByUserId(anyLong())).thenReturn(qualifications);

        List<Object[]> result = recommendationService.getQualificationsByUserId(1L);

        assertEquals(qualifications, result);
    }
}