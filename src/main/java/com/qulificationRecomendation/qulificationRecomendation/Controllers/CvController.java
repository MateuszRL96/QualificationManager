package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Entity.CvData;
import com.qulificationRecomendation.qulificationRecomendation.PdfParser;
import com.qulificationRecomendation.qulificationRecomendation.Repo.CvDataRepository;
import com.qulificationRecomendation.qulificationRecomendation.Services.CvDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cv") // localhost:8080/api/cv
public class CvController {

    private static final Logger logger = LoggerFactory.getLogger(CvController.class);

    @Autowired
    private CvDataService cvDataService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Plik jest pusty");
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Files.createTempFile("cv", ".pdf");
            Files.write(path, bytes);
            return ResponseEntity.ok("Plik przesłany pomyślnie: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas przesyłania pliku");
        }
    }

    @PostMapping("/extract")
    public ResponseEntity<Map<String, List<String>>> extractData(@RequestParam("file") MultipartFile file) {
        try {
            // Zapisz plik tymczasowo
            Path tempFile = Files.createTempFile("cv", ".pdf");
            Files.write(tempFile, file.getBytes());

            // Odczytaj tekst
            String text = PdfParser.extractText(tempFile.toString());

            // Przetwórz dane (edukacja, kariera, umiejętności)
            Map<String, List<String>> extractedData = processCvText(text);

            // Usuń plik tymczasowy
            Files.delete(tempFile);

            // Logowanie przetworzonych danych
            logger.info("Extracted Data: {}", extractedData);

            return ResponseEntity.ok(extractedData);
        } catch (IOException e) {
            logger.error("Error during file processing", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private Map<String, List<String>> processCvText(String text) {
        Map<String, List<String>> extractedData = new HashMap<>();
        extractedData.put("Edukacja", extractSection(text, "Edukacja"));
        extractedData.put("Kariera", extractSection(text, "Doswiadczenie zawodowe"));
        extractedData.put("Umiejetnosci", extractSkillsSection(text, "Umiejetnosci"));
        return extractedData;
    }

    private List<String> extractSection(String text, String sectionName) {
        List<String> extractedLines = new ArrayList<>();
        boolean sectionFound = false;
        for (String line : text.split("\n")) {
            if (line.trim().toLowerCase().contains(sectionName.toLowerCase())) {
                sectionFound = true;
                continue;
            }
            if (sectionFound) {
                if (line.trim().isEmpty() || line.matches("^[A-Z].*")) break; // End of section
                extractedLines.add(line.trim());
            }
        }
        return extractedLines;
    }

    private List<String> extractSkillsSection(String text, String sectionName) {
        List<String> extractedLines = new ArrayList<>();
        boolean sectionFound = false;
        for (String line : text.split("\n")) {
            if (line.trim().toLowerCase().startsWith(sectionName.toLowerCase())) {
                sectionFound = true;
                continue;
            }
            if (sectionFound) {
                if (line.trim().isEmpty() || line.matches("^[A-Z].*")) break; // End of section
                String[] skills = line.split(",");
                for (String skill : skills) {
                    extractedLines.add(skill.trim());
                }
                break;
            }
        }
        return extractedLines;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveCvData(@RequestBody Map<String, List<String>> extractedData) {
        CvData cvData = new CvData();
        cvData.setEdukacja(extractedData.get("Edukacja"));
        cvData.setKariera(extractedData.get("Kariera"));
        cvData.setUmiejetnosci(extractedData.get("Umiejetnosci"));
        cvDataService.saveCvData(cvData);
        return ResponseEntity.ok("Dane CV zapisane pomyślnie");
    }

}