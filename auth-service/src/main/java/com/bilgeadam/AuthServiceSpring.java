package com.bilgeadam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
/**
 * Feign Cliet aktivasyonu için burada kullanılması gerekli.
 * Not!! ARAŞTIRALIM
 */
@EnableFeignClients
public class AuthServiceSpring {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceSpring.class);
    }
}
