package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.PdfParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cv") // localhost:8080/api/cv
public class CvController {

    private static final Logger logger = LoggerFactory.getLogger(CvController.class);

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Plik jest pusty");
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("uploads/" + file.getOriginalFilename());
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

            // Przetwórz dane (edukacja i kariera)
            Map<String, List<String>> extractedData = processCvText(text);

            // Usuń plik tymczasowy
            Files.delete(tempFile);

            // Logowanie przetworzonych danych
            logger.info("Extracted Data: {}", extractedData);

            return ResponseEntity.ok(extractedData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private Map<String, List<String>> processCvText(String text) {
        Map<String, List<String>> extractedData = new HashMap<>();
        extractedData.put("Edukacja", extractSection(text, "Edukacja"));
        extractedData.put("Kariera", extractSection(text, "Doświadczenie zawodowe"));
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
                if (line.trim().isEmpty()) break; // Koniec sekcji
                extractedLines.add(line.trim());
            }
        }
        return extractedLines;
    }


}