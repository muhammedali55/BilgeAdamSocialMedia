package com.bilgeadam.service;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.UserFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void saveReturnUser() {
        RegisterRequestDto  registerRequestDto =
                UserFactory.buildRegisterRequestDto();
        User user = userService.saveReturnUser(registerRequestDto);
        assertTrue(user.getId() > 0);
    }

    @Test
    void findAll() {
    }

    @Test
    void isUser() {
    }

    @Test
    void getProfile() {
    }
}