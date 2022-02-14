package com.bilgeadam.controller;

import static com.bilgeadam.constant.RestApiUrls.*;
import com.bilgeadam.dto.request.ProfileRequestDto;
import com.bilgeadam.repository.entity.Profile;
import com.bilgeadam.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(VERSION+PROFILE)
// @RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping(SAVE)
    // @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody @Valid ProfileRequestDto dto){
        String id = profileService.save(dto);
        return ResponseEntity.ok(id);
    }

    @PostMapping(FINDBYAUTHID)
    public ResponseEntity<String> findByAuthId(long authid){
        Optional<Profile> profile = profileService.findByAuthId(authid);
        if(profile.isPresent()){
            return ResponseEntity.ok(profile.get().getId());
        }else{
            return ResponseEntity.ok("");
        }
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Profile>> findAll(){
        return ResponseEntity.ok(profileService.findAll());
    }


}
