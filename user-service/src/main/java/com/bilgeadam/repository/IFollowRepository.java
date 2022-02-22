package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Follow;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowRepository extends MongoRepository<Follow, String> {
}
