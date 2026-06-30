package com.aryan.dailyjournal.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.aryan.dailyjournal.entity.User;

public interface UserRepository extends MongoRepository<User,ObjectId>{

    User findByUsername(String username);

}
