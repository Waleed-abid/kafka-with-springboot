package com.example.springkafkagpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void deleteUser(String id) {
        userRepository.deleteById(id);
        kafkaTemplate.send("user-topic", new User(id, null, 0, null));
    }
}

