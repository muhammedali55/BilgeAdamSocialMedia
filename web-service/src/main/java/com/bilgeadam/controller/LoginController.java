package com.bilgeadam.controller;

import com.bilgeadam.dto.request.LoginDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.model.LoginPageModel;
import com.bilgeadam.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // localhost/login
    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        /**
         * template altındaki html page ile aynı olmalıdır.
         * public altında ki html file ları KULLANILAMAZ
         */
        modelAndView.setViewName("login");
        /*
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
        */
        return modelAndView;
    }

   // @PostMapping("/login")
    public Object login(@Valid LoginDto loginDto) {
        DoLoginResponseDto  response = loginService.Login(loginDto);
        /**
         * 200 ile kullanınını profil bilgisiini olsuğunu mğrendim
         * profileid= 45435bcgbrtyb
         */
        if(response.getError() == 200){
            /**
             * Ana Sayfa kim için açılıyor.
             * Kullanıcı Yetkili mi? Session - Oturumu var mı?
             */
            return "redirect:/"; // redirect yönlendirme için kullalır. gitmek istediğiniz url yi yazrsınız.
        }else{
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            if(response.getError() == 410)
                modelAndView.addObject("loginerror", "Kullanıcıadı ya da şifre hatalı");
            else if(response.getError() == 500)
                modelAndView.addObject("loginerror", "Beklenmeyen Hata Lütfen Tekrar Deneyiniz.");
            //modelAndView.addObject("hata",true);
            return modelAndView;
        }
    }

}
