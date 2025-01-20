package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth") //-> localhost:8080/api/auth/login-count
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login-count")
    public ResponseEntity<List<Map<String, Object>>> countLoginsByDate() {
        List<Map<String, Object>> loginCounts = authService.countLoginsByDate();
        return ResponseEntity.ok(loginCounts);
    }
}
