package com.bilgeadam.controller;

import com.bilgeadam.model.LoginPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    // localhost/login
    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        /**
         * template altındaki html page ile aynı olmalıdır.
         * public altında ki html file ları KULLANILAMAZ
         */
        modelAndView.setViewName("login");
        List<LoginPageModel.urun> list = new ArrayList<>();
        list.add(LoginPageModel.urun.builder().urunAdi("PC").build());
        list.add(LoginPageModel.urun.builder().urunAdi("LAPTOP").build());

        modelAndView.addObject("model",
                LoginPageModel
                        .builder()
                        .title("Üye Ol!!!")
                        .urunler(list)
                        .build());
        modelAndView.addObject("model2",
                "Başka Model");
        return modelAndView;
    }
}
