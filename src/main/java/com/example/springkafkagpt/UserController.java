package com.example.springkafkagpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
//        kafkaTemplate.send("user-topic", user);
//        userService.addUser(user);
        return user;
    }
}
