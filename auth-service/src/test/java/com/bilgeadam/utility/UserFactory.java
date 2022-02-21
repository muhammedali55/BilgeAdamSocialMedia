package com.bilgeadam.utility;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.repository.entity.User;

public class UserFactory {

    public static User createUser(){
        User user = new User();
        user.setUsername("test-user");
        user.setPassword("test-123");
        return user;
    }

    public static RegisterRequestDto createRegisterRequestDto(){
       RegisterRequestDto dto = new RegisterRequestDto();
       dto.setEmail("text@gmail.com");
       dto.setSoyad("test-soyad");
       dto.setAd("test-ad");
       dto.setSifre("test-123");
       dto.setUlke("test-ulke");
       dto.setYil(2001);
       return dto;
    }
}
