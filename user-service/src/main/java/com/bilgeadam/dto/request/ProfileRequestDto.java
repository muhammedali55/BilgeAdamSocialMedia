package com.bilgeadam.dto.request;

import com.bilgeadam.repository.entity.Interest;
import com.bilgeadam.repository.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileRequestDto {
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
    Education education;
    Work work;


    class Education{
        String name;
        int from;
        int to;
        String about;
    }


    class Work{
        String company;
        String designation;
        int from;
        int to;
        String town;
        String description;
    }

}
