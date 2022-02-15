package com.bilgeadam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {
    @GetMapping("")
    public ResponseEntity<String> fallback() {
        return ResponseEntity.ok("Bu servis şuan hizmet veremiyor.");
    }
}
