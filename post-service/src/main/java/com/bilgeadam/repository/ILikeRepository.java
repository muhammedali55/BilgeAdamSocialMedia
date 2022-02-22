package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Like;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeRepository  extends MongoRepository<Like, String> {
}
