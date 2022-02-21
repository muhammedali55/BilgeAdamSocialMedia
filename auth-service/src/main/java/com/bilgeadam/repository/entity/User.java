package com.bilgeadam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tbluser")
@Entity
public class User implements Serializable {

    @SequenceGenerator(name = "sq_tbluser_id",sequenceName = "sq_tbluser_id",allocationSize = 1,initialValue = 1)
    @GeneratedValue(generator = "sq_tbluser_id")
    // Bu 1. Adet sq oluşturur, bütün tablolarda sayı sürekli artar.
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    long id;
    String username;
    String password;
    /**
     * Status kullanıcının aktiflik durumunu belirtir.
     * 0-> pasif kullanıcı
     * 1-> aktif kullanıcı
     * 2-> engellenmiş kullanıcı  , hesap askıda
     * 3-> V.S.
     */
    int status;
    long createDate;
    long updateDate;
}
