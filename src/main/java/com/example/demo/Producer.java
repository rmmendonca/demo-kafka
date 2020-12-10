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
    
    @Autowired
    private KafkaTemplate<String, String> stringKafkaTemplate;


    public void send() {
        greetingKafkaTemplate.send("topico.coisoquenaoexisteainda", new Greeting("hello", "world"));
        System.out.println("Mensagem enviada");
    }
    
    public void sendString(String topico, String s) {
    	stringKafkaTemplate.send(topico, s);
        System.out.println("Mensagem String enviada");
    }

}
