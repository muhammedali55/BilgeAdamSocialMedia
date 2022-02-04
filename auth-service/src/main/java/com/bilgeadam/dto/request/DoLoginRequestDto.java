package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoLoginRequestDto {
    @Email(message = "Lütfen geçerli bir email adresi giriniz")
    @NotNull(message = "EMail adresi boş geçilemez")
    String username;
    @NotNull
    @Size(max = 32,min = 8,message = "Email adresi Enaz 8 Karakter ve En fazla 32 karakter olabilir")
    String password;
}
