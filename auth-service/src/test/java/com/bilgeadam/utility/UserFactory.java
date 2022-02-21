package com.bilgeadam.utility;

import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.repository.entity.User;

public class UserFactory {

    public static User buildUser(){
        User user = new User();
        user.setPassword("123");
        user.setUsername("bilgeadam");
        return user;
    }

    public static DoLoginRequestDto builDoLoginRequestDto(){
        DoLoginRequestDto doLoginRequestDto = new DoLoginRequestDto();
        doLoginRequestDto.setPassword("123");
        doLoginRequestDto.setUsername("bilgeadam");
        return doLoginRequestDto;
    }

    public static RegisterRequestDto buildRegisterRequestDto(){
        RegisterRequestDto dto = new RegisterRequestDto();
        dto.setAd("bilgeadam");
        dto.setCinsiyet("erkek");
        dto.setEmail("bilgeadaç@gmail.com");
        dto.setSoyad("adam");
        dto.setUlke("Türkiye");
        dto.setSifre("123");
        dto.setYil(1995);
        return dto;
    }

}
