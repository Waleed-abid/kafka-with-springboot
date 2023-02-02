package com.example.springkafkagpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private KafkaProducer kafkaProducer;
    private String kafkaTopicData;
    @PostMapping("/user")
    public Object createUser(@RequestBody User user) {
        user.setOperation("create");
        kafkaProducer.createUser("user", user);
        return user;
    }

    @PutMapping("/user/{id}")
    public Object updateUser(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        user.setOperation("update");
        kafkaProducer.updateUser("user", user);
        return user;
    }

    @DeleteMapping("/user/{id}")
    public Object deleteUser(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        System.out.println(id);

        user.setOperation("delete");
        kafkaProducer.deleteUser("user", user);
        return user;
    }
}
