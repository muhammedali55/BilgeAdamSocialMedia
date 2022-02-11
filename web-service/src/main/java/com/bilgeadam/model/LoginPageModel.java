package com.bilgeadam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginPageModel {
    String title;
    List<urun> urunler;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class urun{
        String urunAdi;
        String urunFiyati;
    }
}
