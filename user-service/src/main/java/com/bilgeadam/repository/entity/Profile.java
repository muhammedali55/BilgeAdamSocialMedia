package com.bilgeadam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Profile implements Serializable {
    @Id
    String id;
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
    /**
     * Bu profilin gizli olup olmadığını tutar. buna göre takip et, takip isteği gönder seçenekleri sunulur.
     */
    boolean ishidden;
    /**
     * Takip ettiklerim
     */
    int follow;
    /**
     * beni takip edenler
     */
    int follower;
    @Document
    @Data
    public static class Education implements Serializable{
        String name;
        int from;
        int to;
        String about;

    }

    @Document
    @Data
    public static class Work implements Serializable{
        String company;
        String designation;
        int from;
        int to;
        String town;
        String description;
    }


}
