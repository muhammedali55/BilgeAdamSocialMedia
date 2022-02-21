package com.bilgeadam.controller;

import com.bilgeadam.config.security.JwtTokenFilter;
import com.bilgeadam.config.security.JwtTokenManager;
import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.manager.ProfileManager;
import com.bilgeadam.mapper.UserMapper;
import com.bilgeadam.rabbitmq.producer.UserServiceProducer;
import com.bilgeadam.repository.IUserRepository;
import com.bilgeadam.service.UserService;
import com.bilgeadam.utility.UserFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    private final static String CONTENT_TYPE_JSON = "application/json";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    IUserRepository iUserRepository;

    @MockBean
    UserMapper userMapper;

    @MockBean
    JwtTokenManager jwtTokenManager;

    @MockBean
    JwtTokenFilter jwtTokenFilter;

    @MockBean
    UserServiceProducer userServiceProducer;

    @MockBean
    ProfileManager profileManager;


    @MockBean
    UserService userService;


    @Test
    public void testDoLogin() throws Exception {
        DoLoginRequestDto dto = UserFactory.builDoLoginRequestDto();
        String dtoString = objectMapper.writeValueAsString(dto);
        ResultActions resultActions =
                mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8")
                                .content(dtoString));
        ArgumentCaptor<DoLoginRequestDto> argumentCaptor = ArgumentCaptor.forClass(DoLoginRequestDto.class);
        //String name =  argumentCaptor.getValue().getUsername();
        Mockito.verify(userService,Mockito.times(1)).getProfile(argumentCaptor.capture());
        Assertions.assertThat(argumentCaptor.getValue().getUsername()).isEqualTo(dto.getUsername());
        Assertions.assertThat(argumentCaptor.getValue().getPassword()).isEqualTo(dto.getPassword());
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

}
