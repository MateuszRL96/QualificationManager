package com.qulificationRecomendation.qulificationRecomendation.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Get user details from authentication
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        String name = oidcUser.getAttribute("name");
        String email = oidcUser.getAttribute("email");
        String token = oidcUser.getIdToken().getTokenValue();

        // Log the token
        logger.info("Token: {}", token);

        // Check if email is null and handle accordingly
        if (email == null) {
            email = name.trim().replaceAll("\\s+", "") + "@example.com";
        }

        // Database connection details
        String url = "jdbc:postgresql://localhost:5433/qualification_db";
        String username = "mateusz";
        String password = "12345678";

        // SQL query to insert a new record
        String sql = "INSERT INTO auth0_user (name, email, login_date) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setObject(3, LocalDateTime.now());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Redirect to the default target URL
        response.sendRedirect("http://localhost:4200");
    }
}