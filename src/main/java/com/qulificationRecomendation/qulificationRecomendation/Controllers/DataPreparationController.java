package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Services.DataPreparationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data-preparation") // -> localhost:8080/data-preparation/user/1/profession
public class DataPreparationController {
    private final DataPreparationService dataPreparationService;

    public DataPreparationController(DataPreparationService dataPreparationService) {
        this.dataPreparationService = dataPreparationService;
    }

    @GetMapping("/user/1/profession")
    public ResponseEntity<String> getProposedProfession() {
        Long userId = 1L; // Hardcoded user_id
        List<Object[]> qualifications = dataPreparationService.getQualificationsByUserId(userId);
        String proposedProfession = dataPreparationService.getProposedProfession(qualifications);
        return ResponseEntity.ok(proposedProfession);
    }
}