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
    public ResponseEntity<String> addQualificationToUser(@RequestBody AddQualificationRequest request) {
        userQualificationService.addQualificationToUser(request.getUserId(), request.getQualificationId(), request.getLevel());
        return ResponseEntity.ok("Qualification added to user successfully");
    }

    @GetMapping("/all")
    public List<Map<String, Object>> getAllUserQualifications() {
        return userQualificationService.getAllUserQualifications();
    }
}
