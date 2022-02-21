package com.bilgeadam.service;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceIntegratedTest {

    @Autowired
    UserService userService;

    @Test
    public void saveUserTest(){
        RegisterRequestDto dto = UserFactory.createRegisterRequestDto();
        User result = userService.saveReturnUser(dto);
        Assertions.assertEquals(dto.getEmail(),result.getUsername());
        Assertions.assertTrue(result.getId()>0);
    }

}
