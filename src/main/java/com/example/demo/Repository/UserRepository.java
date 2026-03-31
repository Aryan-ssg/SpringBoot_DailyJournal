package com.example.demo.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


import com.example.demo.entity.User;

public interface UserRepository extends MongoRepository<User,ObjectId>{

    User findByUsername(String username);

}
