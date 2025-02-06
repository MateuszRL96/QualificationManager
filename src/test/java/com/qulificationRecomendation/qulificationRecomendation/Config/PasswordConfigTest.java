package com.qulificationRecomendation.qulificationRecomendation.Config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordConfigTest {

    private final PasswordConfig passwordConfig = new PasswordConfig();

    @Test
    void passwordEncoderReturnsNonNullInstance() {
        PasswordEncoder passwordEncoder = passwordConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
    }

    @Test
    void passwordEncoderReturnsBCryptPasswordEncoderInstance() {
        PasswordEncoder passwordEncoder = passwordConfig.passwordEncoder();
        assertTrue(passwordEncoder instanceof BCryptPasswordEncoder);
    }
}