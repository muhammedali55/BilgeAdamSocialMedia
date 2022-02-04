package com.bilgeadam.controller;

import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.mapper.UserMapper;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    // ReturnType
    // -> returnCode-> error->9XXX -> 9001-> username and password error
    // -> success-> 1XXX -> 1000, 1100
    // Burada validasyon yapılmalı.
    @PostMapping("/dologin")
    @Operation(summary = "Kullanıcı girişi için kullanılacak metod")
    public ResponseEntity<User> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        Optional<User> user = userService.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user.isPresent())
            return ResponseEntity.ok(user.get());
        return ResponseEntity.ok(new User());
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDto dto){
        // 1. Etapta-> Auth içni kayıt olmalı
        userService.saveReturnUser(dto);
        // 2. Etapta-> User-Service e kayıt için istek atmalı, dönen cevaba göre işle devam etmeli.
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findall")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }


}
