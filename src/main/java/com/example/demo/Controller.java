package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private Producer producer;

    @GetMapping("/send")
    public String send() {
        producer.send();
        return "Ola";
    }
    
    @GetMapping("/send-string")
    public String sendString() {
        producer.sendString("topico.coiso.string", "mensagemString");
        return "Ola String!";
    }

}
