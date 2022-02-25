package com.bilgeadam.controller;

import static com.bilgeadam.constant.RestApiUrls.*;

import com.bilgeadam.dto.request.FindByAutIdDto;
import com.bilgeadam.dto.request.FindByIdRequestDto;
import com.bilgeadam.dto.request.IsProfileExistsDto;
import com.bilgeadam.dto.request.ProfileRequestDto;
import com.bilgeadam.rabbitmq.model.ProfileNotification;
import com.bilgeadam.rabbitmq.producer.ElasticProfileProducer;
import com.bilgeadam.repository.entity.Profile;
import com.bilgeadam.service.ProfileService;
import com.bilgeadam.utility.ResultObject;
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
@CrossOrigin(origins = "*")
public class ProfileController {

    private final ProfileService profileService;

    private final ElasticProfileProducer elasticProfileProducer;

    @PostMapping(SAVE)
    // @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody @Valid ProfileRequestDto dto){
        String id = profileService.save(dto);
        elasticProfileProducer.sendMessageProfileSave(ProfileNotification.builder()
                        .city(dto.getCity())
                        .country(dto.getCountry())
                        .email(dto.getEmail())
                        .firstname(dto.getFirstname())
                        .lastname(dto.getLastname())
                        .profileid(id)
                .build());
        return ResponseEntity.ok(id);
    }


    @PostMapping(FINDBYID)
    public ResponseEntity<ResultObject> findById(@RequestBody @Valid FindByIdRequestDto dto){
        return ResponseEntity.ok(profileService.findById(dto));
    }


    @PostMapping(FINDBYAUTHID)
    public ResponseEntity<String> findByAuthId(@RequestBody @Valid FindByAutIdDto dto){
        Optional<Profile> profile = profileService.findByAuthId(dto.getAuthid());
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

    @PostMapping("/isprofileexistbyid")
    public ResponseEntity<Boolean> isProfileExistById(@RequestBody IsProfileExistsDto dto){
        Optional<Profile> profile = profileService.findById(dto.getProfileId());
        if(profile.isPresent()){
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }
    }
}
