package com.bilgeadam.controller;

import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.ProfileRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import static com.bilgeadam.constant.RestApiUrls.*;

import com.bilgeadam.manager.ProfileManager;
import com.bilgeadam.rabbitmq.model.Notification;
import com.bilgeadam.rabbitmq.producer.UserServiceProducer;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(VERSION+AUTH)
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProfileManager profileManager;

    @Autowired
    UserServiceProducer userServiceProducer;

    // ReturnType
    // -> returnCode-> error->9XXX -> 9001-> username and password error
    // -> success-> 1XXX -> 1000, 1100
    // Burada validasyon yapılmalı.
    @PostMapping(DOLOGIN)
    @Operation(summary = "Kullanıcı girişi için kullanılacak metod")
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        return ResponseEntity.ok(userService.getProfile(dto));
    }

    @PostMapping("/sendmessage")
    public ResponseEntity<Void> sendMessage(String message){
        userServiceProducer.sendMessage(Notification.builder()
                        .message(message)
                .build());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/deleteuser")
    public ResponseEntity<Void> deleteUser(String message){
        userServiceProducer.deleteUser(Notification.builder()
                .message(message)
                .build());
        return ResponseEntity.ok().build();
    }

    // http://localhost:8091/v1/auth/hello
    @GetMapping("/hello")
    public String Hello(){
            log.info("Hello sayfasına geldiniz.");
            log.error("Bir hata oluştu nedensiz ???");
            return "Hello";
    }

    @PostMapping(REGISTER)
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequestDto dto){
        // 1. Etapta-> Auth içni kayıt olmalı
        User user =userService.saveReturnUser(dto);
        // 2. Etapta-> User-Service e kayıt için istek atmalı, dönen cevaba göre işle devam etmeli.
        // authid
        // Uer-Service -> Profile -> oluşturuyorum.
      String id =  profileManager.save(ProfileRequestDto.builder()
                        .authid(user.getId())
                        .email(dto.getEmail())
                        .firstname(dto.getAd())
                        .lastname(dto.getSoyad())
                        .country(dto.getUlke())
                        .city(dto.getSehir())
                        .gender(dto.getCinsiyet())
                .build()).getBody();
        return ResponseEntity.ok(id);
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }


}
