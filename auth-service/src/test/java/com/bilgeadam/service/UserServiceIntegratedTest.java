package com.bilgeadam.service;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceIntegratedTest {

    @Autowired
    UserService userService;


    public void saveUserTest(){
        RegisterRequestDto dto = UserFactory.createRegisterRequestDto();
        UserService spy = Mockito.mock(UserService.class);
        Mockito.doNothing().when(spy).clearCache();
        User result = userService.saveReturnUser(dto);
        Assertions.assertEquals(dto.getEmail(),result.getUsername());
        Assertions.assertTrue(result.getId()>0);
    }

}
