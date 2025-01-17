package com.qulificationRecomendation.qulificationRecomendation.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Database connection details
        String url = "jdbc:postgresql://localhost:5433/qualification_db";
        String username = "mateusz";
        String password = "12345678";

        // Get user details from authentication
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");

        // Check if email is null and handle accordingly
        if (email == null) {
            email = name.trim().replaceAll("\\s+", "") + "@example.com";
        }

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
        response.sendRedirect("/");
    }
}