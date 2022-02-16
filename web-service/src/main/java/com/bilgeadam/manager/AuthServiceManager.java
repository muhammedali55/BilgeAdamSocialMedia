package com.bilgeadam.manager;

import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.bilgeadam.constants.RestApiUrls.DOLOGIN;

@FeignClient(url = "localhost:8091/v1/auth", name = "userServiceFeignClient", decode404 = true)
public interface AuthServiceManager {

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody @Valid RegisterRequestDto dto);

    @PostMapping(DOLOGIN)
    ResponseEntity<DoLoginResponseDto> doLogin(DoLoginRequestDto dto);

}
