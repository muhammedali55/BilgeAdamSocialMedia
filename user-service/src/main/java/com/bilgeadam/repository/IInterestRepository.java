package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Interest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInterestRepository extends MongoRepository<Interest, String> {
}
