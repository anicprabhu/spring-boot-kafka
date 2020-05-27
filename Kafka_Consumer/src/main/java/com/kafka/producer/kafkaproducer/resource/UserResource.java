package com.kafka.producer.kafkaproducer.resource;

import com.kafka.producer.kafkaproducer.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {

	@Autowired
    KafkaTemplate<String, User> kafkaTemplate;
	
	private static final String TOPIC = "Kafka_Example";
    
    @GetMapping("/consume/{message}")
    public String post(@PathVariable("message") final String message){
                
        User user = new User("Baba", "bondu", "0.001");

		kafkaTemplate.send(TOPIC,user);
    	
        return "Published Sucessfully";
    }
}