package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Follower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowerRepository extends MongoRepository<Follower, String> {
}
