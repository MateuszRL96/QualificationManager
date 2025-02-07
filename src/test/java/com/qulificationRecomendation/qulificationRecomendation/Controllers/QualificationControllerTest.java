package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Qualification;
import com.qulificationRecomendation.qulificationRecomendation.Services.QualificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class QualificationControllerTest {

    @Mock
    private QualificationService qualificationService;

    @InjectMocks
    private QualificationController qualificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addQualificationReturnsSavedQualification() {
        Qualification qualification = new Qualification();
        OAuth2User principal = mock(OAuth2User.class);
        when(qualificationService.addQualification(any(Qualification.class), any(OAuth2User.class))).thenReturn(qualification);

        ResponseEntity<Qualification> response = qualificationController.addQualification(qualification, principal);

        assertEquals(ResponseEntity.ok(qualification), response);
    }

    @Test
    void getAllQualificationsReturnsListOfQualifications() {
        List<Qualification> qualifications = Collections.singletonList(new Qualification());
        when(qualificationService.getAllQualifications()).thenReturn(qualifications);

        ResponseEntity<List<Qualification>> response = qualificationController.getAllQualifications();

        assertEquals(ResponseEntity.ok(qualifications), response);
    }

    @Test
    void deleteQualificationReturnsNoContent() {
        doNothing().when(qualificationService).deleteQualification(anyLong());

        ResponseEntity<Void> response = qualificationController.deleteQualification(1L);

        assertEquals(ResponseEntity.noContent().build(), response);
    }

    @Test
    void getQualificationWithSkillsReturnsQualification() {
        Qualification qualification = new Qualification();
        when(qualificationService.getQualificationWithSkills(anyLong())).thenReturn(Optional.of(qualification));

        ResponseEntity<Qualification> response = qualificationController.getQualificationWithSkills(1L);

        assertEquals(ResponseEntity.ok(qualification), response);
    }

    @Test
    void getQualificationWithSkillsReturnsNotFound() {
        when(qualificationService.getQualificationWithSkills(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Qualification> response = qualificationController.getQualificationWithSkills(1L);

        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    void findQualificationIdsByNameReturnsListOfIds() {
        List<Long> ids = Collections.singletonList(1L);
        when(qualificationService.findQualificationIdsByName(anyString())).thenReturn(ids);

        ResponseEntity<List<Long>> response = qualificationController.findQualificationIdsByName("Java");

        assertEquals(ResponseEntity.ok(ids), response);
    }

    @Test
    void getQualificationsByProfessionReturnsListOfQualifications() {
        List<String[]> qualifications = Collections.singletonList(new String[]{"Qualification1"});
        when(qualificationService.getQualificationsByProfession(anyString())).thenReturn(qualifications);

        ResponseEntity<List<String[]>> response = qualificationController.getQualificationsByProfession("Software Engineer");

        assertEquals(ResponseEntity.ok(qualifications), response);
    }
}