package com.example.springkafkagpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SpringkafkagptApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringkafkagptApplication.class, args);
	}

}



