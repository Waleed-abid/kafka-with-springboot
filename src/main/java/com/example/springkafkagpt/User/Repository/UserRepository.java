package com.example.springkafkagpt.User.Repository;

import com.example.springkafkagpt.User.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
