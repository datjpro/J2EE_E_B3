package com.example.demoj2ee.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {
    @GetMapping("/hello")

    public String home() {
        return "Hello bé Ngân ";
    }

}