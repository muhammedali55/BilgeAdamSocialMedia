package com.bilgeadam.dto.request;

import com.bilgeadam.repository.entity.Interest;
import com.bilgeadam.repository.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileRequestDto implements Serializable {
    String token;
    long authid;
    String firstname;
    String lastname;
    String email;
    String birthdate;
    String country;
    String city;
    String gender;
    String about;
    List<Interest> interest;
    Profile.Education education;
    Profile.Work work;


}
