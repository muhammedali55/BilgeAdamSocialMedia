package com.bilgeadam.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    MvcTokenFilter  mvcTokenFilter(){
        return new MvcTokenFilter();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * Eğer, RestApi izinlere takılmadan kullanmak için etkinleştiriyoruz.
         * Web, csrf etkin kalmalı.
         * Post isterklerini alırken sıkıntı oluyor. post ları reddediyor.
         * Get isterkleri alınır.
         */
         http.csrf().disable();
        /**
         * Http isteklerinde, kimliklendirme işlemlerine takılmış isteklerin, tamamında, izinleri sorgulama.
         * Tüm isteklere izin ver.
         */
         // http.authorizeRequests().anyRequest().permitAll();
        /**
         * Bu orjinal configürasyonda olan komutlar. tüm istekleri login e yönlendir.
         */

        /**
         * Bu kısım Orjinal Komut kısmımız.
         * form ile kullanıcıyı yetkilendirmek üzere bir web sayfasına yönlendirir.
         * httpbasic ile kullanıdan bilgi girişi yapması için ilgili browser ın tasarımına yönlendirecektir.
         */
       /*
        http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
        http.formLogin();
        http.httpBasic();
*/
        http.authorizeRequests()
                /**
                 * http://localhost/register adresine gitmek isterse izin ver.
                 */
                .antMatchers("/register","/login").permitAll()
                /**
                 * diğer bütün istekleri izine tabi tut.
                 */
                .anyRequest().authenticated(); //403
        /**
         * Eğer özellikle belirtilmemiş ise login formu spring tarafındfan sağlanır ve yönetilir.
         */
         /*
         http.formLogin();
         */
        /**
         * Artık, yetkisiz alanlara girişte kendi tanımladığımız formu kullanır.
         */
        http.formLogin().loginPage("/login").loginProcessingUrl("/login");
       // http.formLogin(form-> form.defaultSuccessUrl("/"));


        /**
         * Bir İsteği Spring Filter ve Sevrlet Filter işleme tabi tutar.
         * Biz burada araya girerek, kendi kullanıcımızı spring e tanıtmalıyız.
         */
  //      http.addFilterBefore(mvcTokenFilter(),UsernamePasswordAuthenticationFilter.class);
    }


}
