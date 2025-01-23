package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.Entity.EmployerDetails;
import com.qulificationRecomendation.qulificationRecomendation.Services.EmployerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employer-details") //localhost:8080/api/employer-details/all
public class EmployerDetailsController {

    @Autowired
    private EmployerDetailsService employerDetailsService;

    @GetMapping("/all")
    public List<EmployerDetails> getAllEmployerDetails() {
        return employerDetailsService.getAllEmployerDetails();
    }
}