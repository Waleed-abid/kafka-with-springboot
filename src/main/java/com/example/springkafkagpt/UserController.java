package com.example.springkafkagpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        String id = savedUser.getId();
        kafkaTemplate.send("user-topic", id, user);
        return savedUser;
    }
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }


}
