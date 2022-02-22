package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.DisLike;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDisLikeRepository  extends MongoRepository<DisLike, String> {
}
