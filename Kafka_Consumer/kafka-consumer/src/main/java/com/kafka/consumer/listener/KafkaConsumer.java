package com.kafka.consumer.listener;

import com.kafka.consumer.model.User;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    
    @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume(String message){
        System.out.println("Consumed Message - "+message);

    }

    @KafkaListener(topics = "Kafka_Example_Json", groupId = "group_json", containerFactory = "userKafkaListenerContainerFactory")
    public void consumeJson(User user){
        System.out.println("Consumed JSON Message - "+ user);

    }

 
}