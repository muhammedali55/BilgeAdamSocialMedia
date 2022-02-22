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
 * Beni takip edenler
 */
public class Follower {
    @Id
    String id;
    String userid;
    /**
     * Beni takip eden kullanıcılar
     */
    String followeruserid;
}
