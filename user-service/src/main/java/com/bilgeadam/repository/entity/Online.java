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
/**
 * KUllanıcıların aktif olup olmadığını tutan sınıf.
 * ayrıca son aktif lik zamanını tutar
 */
public class Online {
    @Id
    String id;
    String userid;
    boolean isactive;
    long lastactive;
}
