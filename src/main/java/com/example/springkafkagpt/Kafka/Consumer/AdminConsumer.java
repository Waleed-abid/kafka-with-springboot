package com.example.springkafkagpt.Kafka.Consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AdminConsumer {
    public AdminConsumer() {
    }

    @KafkaListener(topics = "user-topic", groupId = "admin")
    public void listen(ConsumerRecord<?, ?> cr) {
        System.out.println("Received message in admin group: " + cr.value());
    }
}
