package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Qualification;
import com.qulificationRecomendation.qulificationRecomendation.Repo.QualificationRepository;
import com.qulificationRecomendation.qulificationRecomendation.Services.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/qualifications")
@CrossOrigin(origins = "http://localhost:4200")
public class QualificationController {
    private final QualificationService qualificationService;

    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @PostMapping
    public ResponseEntity<Qualification> addQualification(@RequestBody Qualification qualification, @AuthenticationPrincipal OAuth2User principal) {
        Qualification savedQualification = qualificationService.addQualification(qualification, principal);
        return ResponseEntity.ok(savedQualification);
    }

    @GetMapping
    public ResponseEntity<List<Qualification>> getAllQualifications() {
        List<Qualification> qualifications = qualificationService.getAllQualifications();
        return ResponseEntity.ok(qualifications);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualification(@PathVariable Long id) {
        qualificationService.deleteQualification(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<Qualification> getQualificationWithSkills(@PathVariable Long id) {
        return qualificationService.getQualificationWithSkills(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search") //localhost:8080/qualifications/search?name=Java
    public ResponseEntity<List<Long>> findQualificationIdsByName(@RequestParam String name) {
        List<Long> qualificationIds = qualificationService.findQualificationIdsByName(name);
        return ResponseEntity.ok(qualificationIds);
    }

    @GetMapping("/profession/{profession}") //localhost:8080/qualifications/profession/Software%20Engineer
    public ResponseEntity<List<String[]>> getQualificationsByProfession(@PathVariable String profession) {
        List<String[]> qualifications = qualificationService.getQualificationsByProfession(profession);
        return ResponseEntity.ok(qualifications);
    }

}