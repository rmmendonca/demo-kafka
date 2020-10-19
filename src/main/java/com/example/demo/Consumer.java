package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "${greeting.topic.name}",
            containerFactory = "greetingKafkaListenerContainerFactory",
            groupId = "${group.bar}")
    public void on(Greeting greeting) {
        System.out.println("Mensagem recebida");
        System.out.println(greeting.getMsg() + ", " + greeting.getName());
    }

}
