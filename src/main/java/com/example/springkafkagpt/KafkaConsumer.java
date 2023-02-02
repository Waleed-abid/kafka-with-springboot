package com.example.springkafkagpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private UserRepository userRepository;
    @KafkaListener(topics = "create")
    public void receive(User user) {
        switch (user.getOperation()) {
            case "create":
                userRepository.save(user);
                break;
            case "update":
                userRepository.save(user);
                break;
            case "delete":
                userRepository.deleteById(user.getId());
                break;      
        }
    }
}