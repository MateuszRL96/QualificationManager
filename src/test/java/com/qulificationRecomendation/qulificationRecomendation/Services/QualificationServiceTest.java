package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Auth0User;
import com.qulificationRecomendation.qulificationRecomendation.Entity.Qualification;
import com.qulificationRecomendation.qulificationRecomendation.Repo.Auth0UserRepository;
import com.qulificationRecomendation.qulificationRecomendation.Repo.QualificationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class QualificationServiceTest {

    @Mock
    private QualificationRepository qualificationRepository;

    @Mock
    private Auth0UserRepository auth0UserRepository;

    @Mock
    private OAuth2User principal;

    @InjectMocks
    private QualificationService qualificationService;

    public QualificationServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserWithQualifications() {
        String email = "mati.kulec12@gmail.com";
        Auth0User user = new Auth0User();
        user.setEmail(email);

        Qualification qualification1 = new Qualification();
        qualification1.setId(1L);
        qualification1.setName("Qualification 1");
        qualification1.setDescription("Description for Qualification 1");
        qualification1.setAuth0User(user);

        when(auth0UserRepository.findTopByEmailOrderByIdDesc(email)).thenReturn(Optional.of(user));
        when(qualificationRepository.findByAuth0User(user)).thenReturn(List.of(qualification1));

        Optional<Auth0User> fetchedUser = qualificationService.getUserWithQualifications(email);

        assertTrue(fetchedUser.isPresent());
        assertEquals(email, fetchedUser.get().getEmail());
        assertEquals(1, fetchedUser.get().getQualifications().size());
        assertEquals("Qualification 1", fetchedUser.get().getQualifications().get(0).getName());

        verify(auth0UserRepository).findTopByEmailOrderByIdDesc(email);
        verify(qualificationRepository).findByAuth0User(user);
    }
    @Test
    public void testPrintUserWithQualifications() {
        String email = "mati.kulec12@gmail.com";
        Auth0User user = new Auth0User();
        user.setEmail(email);

        Qualification qualification1 = new Qualification();
        qualification1.setId(1L);
        qualification1.setName("Qualification 1");
        qualification1.setDescription("Description for Qualification 1");
        qualification1.setAuth0User(user);

        when(auth0UserRepository.findTopByEmailOrderByIdDesc(email)).thenReturn(Optional.of(user));
        when(qualificationRepository.findByAuth0User(user)).thenReturn(List.of(qualification1));

        qualificationService.printUserWithQualifications(email);

        verify(auth0UserRepository).findTopByEmailOrderByIdDesc(email);
        verify(qualificationRepository).findByAuth0User(user);
    }

}