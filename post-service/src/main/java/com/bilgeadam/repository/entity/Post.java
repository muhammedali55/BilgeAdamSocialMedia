package com.bilgeadam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Post {
    @Id
    String id;
    String content;
    String userid;
    String username;
    /**
     * String profileimage
     * S3 Bucket'daki user-profile-image
     * profile imageleri burada tutuyorum.
     * userid üzerinden turyorum.
     * def465433456-> def465433456.jpg
     */
     String postmedia;
    /**
     * en çok like alan gönderiler
     * bugün öne çıkanlar v.s.
     * belli bilgileri ayrı bir tabloda tutun
     * ancak aktif kullandığınız tabloda bu verileri
     * de kullanını.
     *
     */
    int like;
    int dislike;
    long sharedtime;
    Location location;


    @Document
    @Builder
   public static class Location{
        String lat;
        String lng;
        String address;
    }

}
