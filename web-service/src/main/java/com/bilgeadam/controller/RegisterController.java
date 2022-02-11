package com.bilgeadam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class RegisterController {

    @PostMapping("/register")
    public Object register(String ad, String soyad,
                           String email, String sifre,
                           String ulke, MultipartFile resim) {
        ModelAndView model = new ModelAndView();
        model.setViewName("register");

        return model;
    }

}
