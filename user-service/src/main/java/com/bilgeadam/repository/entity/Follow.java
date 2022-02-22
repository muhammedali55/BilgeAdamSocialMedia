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
 * Benim Takip Ettiklerim. user olarak ben takip ediyorum. kimi followuserid
 */
public class Follow {
    @Id
    String id;
    /**
     * Takip Eden
     */
    String userid;
    /**
     * Takip Edilen
     */
    String followuserid;
}
