package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.objectsForTopic.Greeting;

@Service
public class Consumer {

    @KafkaListener(topics = "topico.coiso",
            containerFactory = "greetingKafkaListenerContainerFactory",
            groupId = "grupo.teste.do.coiso.do.inicio") //faz overide do grupo de consumo da configuração do containerFactory
    public void on(Greeting greeting) throws InterruptedException {
        System.out.println("Mensagem recebida");
        System.out.println("dormiu..");
        Thread.sleep(10000);
        System.out.println("acordou..");
        System.out.println(greeting.getMsg() + ", " + greeting.getName());
    }

}
