package com.example.springkafkagpt;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final KafkaTemplate<String, User> kafkaTemplate;
    private final MongoTemplate mongoTemplate;

    public UserService(KafkaTemplate<String, User> kafkaTemplate, MongoTemplate mongoTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.mongoTemplate = mongoTemplate;
    }

    public void addUser(User user) {
        kafkaTemplate.send("user", user);
        mongoTemplate.save(user);
    }
}
