package com.example.springkafkagpt.User.Service;

import com.example.springkafkagpt.User.Model.User;
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

    public User addUser(User user) {
        User savedUser = mongoTemplate.save(user);
        String id = savedUser.getId();
        kafkaTemplate.send("user-topic", id, user);
        return user;
    }
    public void deleteUser(String id) {
        User user = mongoTemplate.findById(id, User.class);
        if (user != null) {
            kafkaTemplate.send("user-topic", user);
            mongoTemplate.remove(user);
        }
    }

}
