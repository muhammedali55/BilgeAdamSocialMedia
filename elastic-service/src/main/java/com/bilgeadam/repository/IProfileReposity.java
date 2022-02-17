package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Profile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface IProfileReposity extends ElasticsearchRepository<Profile, String> {
    /**
     * KUllanıcı ilk adına göre arama yapar. aynı zamanda arama işlemini A* şeklinde yapacaktır.
     * @param firstname
     * @return
     */
    List<Profile> findByFirstnameLike(String firstname);


}
