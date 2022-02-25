package com.bilgeadam.controller;

import com.bilgeadam.config.security.JwtTokenFilter;
import com.bilgeadam.config.security.JwtTokenManager;
import com.bilgeadam.manager.ProfileManager;
import com.bilgeadam.rabbitmq.producer.UserServiceProducer;
import com.bilgeadam.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.exceptions.util.ScenarioPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JwtTokenFilter jwtTokenFilter;

    @MockBean
    JwtTokenManager jwtTokenManager;


    @InjectMocks
    UserService userService;

    @MockBean
    ProfileManager profileManager;

    @MockBean
    UserServiceProducer userServiceProducer;


    public void testMessage() throws Exception {

     ResultActions result = mockMvc.perform(MockMvcRequestBuilders.request(
                HttpMethod.GET,"/v1/auth/messsage").header(
                        "mymessage","SElam Nasılsın")
                     .characterEncoding("UTF-8")
                     .accept(MediaType.APPLICATION_JSON));


        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(userService)
                .merhaba(argumentCaptor.capture());
        Assertions.assertEquals("SElam Nasılsın",argumentCaptor.getValue());
        result.andExpect(MockMvcResultMatchers.status().isOk());

    }


}
