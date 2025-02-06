package com.qulificationRecomendation.qulificationRecomendation.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundExceptionTest {

    @Test
    void getMessageReturnsCorrectMessage() {
        String message = "User not found";
        UserNotFoundException exception = new UserNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void getLocalizedMessageReturnsCorrectMessage() {
        String message = "User not found";
        UserNotFoundException exception = new UserNotFoundException(message);
        assertEquals(message, exception.getLocalizedMessage());
    }

    @Test
    void getCauseReturnsNullWhenNoCauseProvided() {
        UserNotFoundException exception = new UserNotFoundException("User not found");
        assertNull(exception.getCause());
    }

    @Test
    void getStackTraceReturnsNonEmptyArray() {
        UserNotFoundException exception = new UserNotFoundException("User not found");
        assertTrue(exception.getStackTrace().length > 0);
    }
}