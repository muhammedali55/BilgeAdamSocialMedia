package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    /**
     * Kullanıcının oturum açabilmesini için email ve şifre bilgisi alınır. kayıtlı bilgi var ise
     * optional olarak cevap dönülür.
     * @param username  E-Mail Bilgisi
     * @param password  Şifre Bilgisi
     * @return
     */
    Optional<User> findByUsernameAndPassword(String username,String password);

    /**
     * Durularına göre kullacılaır listeler.
     * @param status
     * @return
     */
    List<User> findByStatus(int status);

}
