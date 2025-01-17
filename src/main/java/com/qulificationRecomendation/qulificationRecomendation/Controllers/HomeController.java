package com.qulificationRecomendation.qulificationRecomendation.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String secured() {
        return "redirect:http://localhost:4200";
    }
}
