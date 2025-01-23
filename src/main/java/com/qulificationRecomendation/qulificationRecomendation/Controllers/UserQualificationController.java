package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.DTO.AddQualificationRequest;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user-qualifications")
public class UserQualificationController {
    @Autowired
    private UserQualificationService userQualificationService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addQualificationToUser(@RequestBody AddQualificationRequest request) {
        userQualificationService.addQualificationToUser(request.getUserId(), request.getQualificationId(), request.getLevel());
        Map<String, String> response = Map.of("message", "Qualification added to user successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{qualificationId}") //localhost:8080/api/user-qualifications/delete/1
    public ResponseEntity<Void> deleteQualificationFromUser(@PathVariable Long qualificationId) {
        userQualificationService.deleteQualificationFromUser(qualificationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<Map<String, Object>> getAllUserQualifications() {
        return userQualificationService.getAllUserQualifications();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getQualificationsByUserId(@PathVariable Long userId) {
        List<Map<String, Object>> qualifications = userQualificationService.getQualificationsByUserId(userId);
        return ResponseEntity.ok(qualifications);
    }
}