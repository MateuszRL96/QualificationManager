package com.qulificationRecomendation.qulificationRecomendation.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddQualificationRequestTest {

    @Test
    void getUserIdReturnsCorrectValue() {
        AddQualificationRequest request = new AddQualificationRequest();
        request.setUserId(1L);
        assertEquals(1L, request.getUserId());
    }

    @Test
    void setUserIdSetsCorrectValue() {
        AddQualificationRequest request = new AddQualificationRequest();
        request.setUserId(1L);
        assertEquals(1L, request.getUserId());
    }

    @Test
    void getQualificationIdReturnsCorrectValue() {
        AddQualificationRequest request = new AddQualificationRequest();
        request.setQualificationId(10L);
        assertEquals(10L, request.getQualificationId());
    }

    @Test
    void setQualificationIdSetsCorrectValue() {
        AddQualificationRequest request = new AddQualificationRequest();
        request.setQualificationId(10L);
        assertEquals(10L, request.getQualificationId());
    }

    @Test
    void getLevelReturnsCorrectValue() {
        AddQualificationRequest request = new AddQualificationRequest();
        request.setLevel("beginner");
        assertEquals("beginner", request.getLevel());
    }

    @Test
    void setLevelSetsCorrectValue() {
        AddQualificationRequest request = new AddQualificationRequest();
        request.setLevel("beginner");
        assertEquals("beginner", request.getLevel());
    }

    @Test
    void setLevelThrowsExceptionForInvalidLevel() {
        AddQualificationRequest request = new AddQualificationRequest();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            request.setLevel("invalid");
        });
        assertEquals("Invalid level: invalid", exception.getMessage());
    }
}