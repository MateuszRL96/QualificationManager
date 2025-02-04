package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api") //-> localhost:8080/api/udemy-category
public class ApiUdemyController {

    @GetMapping("/udemy-category-data")
    public String getUdemyCategoryData() {
        return "Udemy Category Data";
    }
    @PostMapping("/udemy-category")
    public String getUdemyCategory() {
        try {
            String requestBody = """
            {
                "page": 1,
                "page_size": 1,
                "ratings": "",
                "instructional_level": [],
                "lang": [],
                "price": [],
                "duration": [],
                "subtitles_lang": [],
                "sort": "popularity",
                "features": [],
                "locale": "en_US",
                "extract_pricing": true
            }
            """;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://udemy-api2.p.rapidapi.com/v1/udemy/category/development"))
                    .header("x-rapidapi-key", "c20d5d78dcmsh7559f2b703e84d4p16a954jsn1b1dd0cc1421")
                    .header("x-rapidapi-host", "udemy-api2.p.rapidapi.com")
                    .header("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            return "Status code: " + response.statusCode() + "\nResponse: " + response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred: " + e.getMessage();
        }
    }
}