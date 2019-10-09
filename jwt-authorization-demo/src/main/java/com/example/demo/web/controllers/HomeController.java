package com.example.demo.web.controllers;

import com.example.demo.web.model.HelloMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/home")
    public HelloMessage home() {
        return new HelloMessage("Hello world");
    }
}
