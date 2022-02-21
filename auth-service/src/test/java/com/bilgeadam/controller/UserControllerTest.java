package com.bilgeadam.controller;

import com.bilgeadam.config.security.JwtTokenFilter;
import com.bilgeadam.config.security.JwtTokenManager;
import com.bilgeadam.manager.ProfileManager;
import com.bilgeadam.rabbitmq.producer.UserServiceProducer;
import com.bilgeadam.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    JwtTokenFilter jwtTokenFilter;

    @MockBean
    JwtTokenManager jwtTokenManager;


    @MockBean
    UserService userService;

    @MockBean
    ProfileManager profileManager;

    @MockBean
    UserServiceProducer userServiceProducer;


    @Test
    public void testMessage() throws Exception {

     MvcResult result = mockMvc.perform(MockMvcRequestBuilders.request(
                HttpMethod.GET,"/v1/auth/messsage").header(
                        "mymessage","SElam Nasılsın")
                     .characterEncoding("UTF-8")
                     .accept(MediaType.APPLICATION_JSON)).andReturn();

     Object robject =  result.getAsyncResult();

    }


}
