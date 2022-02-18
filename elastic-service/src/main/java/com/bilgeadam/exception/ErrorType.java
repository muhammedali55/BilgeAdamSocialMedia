package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    AUTH_KULLANICI_SIFRE_HATASI(1902,"Kullaıcı adı veya şifre hatalı",HttpStatus.FORBIDDEN),
    INTERNAL_ERROR(1903,"Sistemde bir hata oluştu",HttpStatus.INTERNAL_SERVER_ERROR),
    AUTH_GECERSIZ_TOKEN(1904,"Kullaıcı adı veya şifre hatalı",HttpStatus.FORBIDDEN),
    BAD_REQUEST_ERROR(1905,"Bad Request",HttpStatus.BAD_REQUEST)
    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
