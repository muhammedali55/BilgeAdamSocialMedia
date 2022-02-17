package com.bilgeadam.controller;

import com.bilgeadam.repository.entity.Profile;
import com.bilgeadam.service.ElasticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/elastic")
@RequiredArgsConstructor
public class ElasticController {

    private final ElasticService elasticService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(Profile profile) {
        elasticService.save(profile);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findbyfirstname")
    public ResponseEntity<List<Profile>> findByFisrtname(String firstname) {
        return ResponseEntity.ok(elasticService.findByFirstNameLike(firstname));
    }

}
