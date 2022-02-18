package com.bilgeadam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoLoginResponseDto {
    String profileid;
    String token;
    /**
     * Status kullanıcının aktiflik durumunu belirtir.
     * 0-> pasif kullanıcı
     * 1-> aktif kullanıcı
     * 2-> engellenmiş kullanıcı  , hesap askıda
     * 3-> V.S.
     */
    int status;
    /**
     * 200: Başarılı
     * 400: Hatalı
     * 410: Kullanıcı bulunamadı
     * 411: token hatası
     * 500: Beklenmeyen hata
     */
    int error;
}
