package com.bilgeadam.manager;

import com.bilgeadam.dto.request.RegisterRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(url = "localhost:8091/v1/user", name = "userServiceFeignClient", decode404 = true)
public interface AuthServiceManager {

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody @Valid RegisterRequestDto dto);
}
