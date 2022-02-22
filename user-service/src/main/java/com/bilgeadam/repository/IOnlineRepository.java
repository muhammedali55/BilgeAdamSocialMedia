package com.bilgeadam.repository;


import com.bilgeadam.repository.entity.Online;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOnlineRepository extends MongoRepository<Online, String> {
}
