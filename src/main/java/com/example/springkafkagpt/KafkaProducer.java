package com.example.springkafkagpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void createUser(String topic, User user) {
        user.setOperation("create");
        user.setId(UUID.randomUUID().toString());
        try {
            String userJson = objectMapper.writeValueAsString(user);
            kafkaTemplate.send(topic,userJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(String topic, User user) {
        user.setOperation("update");
        try {
            String userJson = objectMapper.writeValueAsString(user);
            kafkaTemplate.send(topic, userJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String topic, User user) {
        user.setOperation("delete");
        try {
            String userJson = objectMapper.writeValueAsString(user);
            kafkaTemplate.send(topic, userJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
