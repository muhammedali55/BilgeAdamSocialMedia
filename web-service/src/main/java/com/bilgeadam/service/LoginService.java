package com.bilgeadam.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean Login(String username, String password) {
        if (username.equals("admin@admin.com") && password.equals("administrator")) {
            return true;
        }
        return false;
    }
}
