package com.bilgeadam.service;
import com.bilgeadam.repository.IUserRepository;
import com.bilgeadam.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    /**
     * Kullanıcıyı kayıt eder
     * @param user
     * @return
     */
    public User saveReturnUser(User user){
        iUserRepository.save(user);
        return user;
    }

    public void save(User user){
        iUserRepository.save(user);
    }


}
