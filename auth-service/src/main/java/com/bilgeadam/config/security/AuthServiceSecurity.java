package com.bilgeadam.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthServiceSecurity extends WebSecurityConfigurerAdapter {

    @Bean
   JwtTokenFilter getJwtTokenFilter(){
       return new JwtTokenFilter();
   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        /**
         * Swagger kullanabilmek için gerekli izinlerin verilmesi gerçekleştirilir. - /swagger-ui/**
         * DİKKAT!!! OpenApi - /v3/api-docs/**
         * Ayrıca kullanıcı login işlemi için gerekli olan ka,sf, girebileceği end point eirşime açılır.
         */
        http.authorizeRequests().antMatchers("/v3/api-docs/**",
                                                        "/swagger-ui/**",
                                                        "/v1/auth/dologin",
                                                        "/v1/auth/validatetoken",
                        "/v1/auth/message","/v1/auth/findall","/v1/auth/register","/v1/auth/clearcache",
                        "/v1/auth/findallpage/**","/actuator/**").permitAll()
                .anyRequest().authenticated();  // yuklarıdaki istisnalar dışındaki tüm istekleri izne tabi tut.
        /**
         * configure methodu bizim tarafımızdan override edildiği için tüm işlemleri devralmış oluyoruz.
         * bu nedenle gelen isteklerin yönetimini burada belirmeliyiz.
         */
        http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
