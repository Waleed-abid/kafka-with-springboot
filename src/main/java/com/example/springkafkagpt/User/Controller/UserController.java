package com.example.springkafkagpt.User.Controller;

import com.example.springkafkagpt.User.Model.User;
import com.example.springkafkagpt.User.Service.UserService;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {
    @Autowired
    private AdminClient adminClient;

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
    @GetMapping("/topics")
    public List<String> getTopics() throws ExecutionException, InterruptedException {
        ListTopicsResult result = adminClient.listTopics();
        return (List<String>) result.names().get();
    }

}
