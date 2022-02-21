package com.bilgeadam.service;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.mapper.UserMapper;
import com.bilgeadam.repository.IUserRepository;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Bu sınıfta Mock yapısını kullanacağımız için MockitoExtension kullanılır.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    /**
     * Autowired -> Gerçek nesneyi kullanıyorsunuz.
     * save -> DB KAyıt etmesini bekleriz.
     * Biz test ettiğimiz için DB ye kayıt yapmak istemiyoruz.
     * Bu yüzden Mock yapısını kullanıyoruz.
     */
    @InjectMocks
    private UserService userService;

    @Mock
    IUserRepository iUserRepository;

    @Mock
    UserMapper userMapper;

    @Test
    public void testSave(){
        RegisterRequestDto dto = UserFactory.createRegisterRequestDto();
        User user = UserFactory.createUser();
        /**
         * Eğer, userMapper un toUser() metodunu RegisterRequestDto ile
         * çağırıldığında, user nesnesini dön.
         */
        Mockito.when(userMapper.toUser(ArgumentMatchers
                .any(RegisterRequestDto.class))).thenReturn(user);
        Mockito.when(iUserRepository.save(ArgumentMatchers.any(User.class)))
                .thenReturn(user);
       User resultUSer =  userService.saveReturnUser(dto);
       Assertions.assertTrue(resultUSer ==null);

    }



}
