package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.objectsForTopic.Greeting;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, Greeting> greetingKafkaTemplate;


    public void send() {
        greetingKafkaTemplate.send("topico.coisoquenaoexisteainda", new Greeting("hello", "world"));
        System.out.println("Mensagem enviada");
    }

}
