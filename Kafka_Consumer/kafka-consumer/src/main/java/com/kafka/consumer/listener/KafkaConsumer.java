package com.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    
    @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume(String message){
        System.out.println("Consumed Message"+message);

    }

    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json")
    public void consumeJson(String message){
        System.out.println("Consumed Message"+message);

    }

 
}