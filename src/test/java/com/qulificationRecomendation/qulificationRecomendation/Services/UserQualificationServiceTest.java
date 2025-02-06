package com.qulificationRecomendation.qulificationRecomendation.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserQualificationServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserQualificationService userQualificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addQualificationToUserInsertsCorrectly() {
        doNothing().when(jdbcTemplate).update(anyString(), anyLong(), anyLong(), anyInt());

        userQualificationService.addQualificationToUser(1L, 10L, "beginner");

        verify(jdbcTemplate, times(1)).update(anyString(), eq(1L), eq(10L), eq(1));
    }

    @Test
    void addQualificationToUserThrowsExceptionForInvalidLevel() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userQualificationService.addQualificationToUser(1L, 10L, "invalid");
        });

        assertEquals("Invalid level: invalid", exception.getMessage());
    }

    @Test
    void getAllUserQualificationsReturnsList() {
        List<Map<String, Object>> qualifications = Collections.singletonList(Collections.singletonMap("key", "value"));
        when(jdbcTemplate.queryForList(anyString())).thenReturn(qualifications);

        List<Map<String, Object>> result = userQualificationService.getAllUserQualifications();

        assertEquals(qualifications, result);
    }

    @Test
    void getQualificationsByUserIdReturnsList() {
        List<Map<String, Object>> qualifications = Collections.singletonList(Collections.singletonMap("key", "value"));
        when(jdbcTemplate.queryForList(anyString(), anyLong())).thenReturn(qualifications);

        List<Map<String, Object>> result = userQualificationService.getQualificationsByUserId(1L);

        assertEquals(qualifications, result);
    }

    @Test
    void deleteQualificationFromUserDeletesCorrectly() {
        doNothing().when(jdbcTemplate).update(anyString(), anyLong());

        userQualificationService.deleteQualificationFromUser(10L);

        verify(jdbcTemplate, times(1)).update(anyString(), eq(10L));
    }
}