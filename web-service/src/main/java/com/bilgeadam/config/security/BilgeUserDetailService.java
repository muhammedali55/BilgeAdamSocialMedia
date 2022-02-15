package com.bilgeadam.config.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class BilgeUserDetailService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       if(username.equals("bilgeadam")){

           return new User("bilgeadam","123",new HashSet<>());
       }
       throw new UsernameNotFoundException("User not found");
    }
}
