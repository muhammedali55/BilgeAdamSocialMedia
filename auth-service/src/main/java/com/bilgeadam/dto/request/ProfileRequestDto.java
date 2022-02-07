package com.bilgeadam.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileRequestDto implements Serializable {
    long authid;
    String firstname;
    String lastname;
    String email;
    String birthdate;
    String country;
    String city;
    String gender;
    String about;

}
