package com.example.springkafkagpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/user")
    public void createUser(@RequestBody User user) {
        user.setOperation("create");
        kafkaProducer.createUser("user", user);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        user.setOperation("update");
        kafkaProducer.updateUser("user", user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        user.setOperation("delete");
        kafkaProducer.deleteUser("user", user);
    }
}
