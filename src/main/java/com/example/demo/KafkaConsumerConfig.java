package com.example.demo;

import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.demo.objectsForTopic.Greeting;

import java.util.HashMap;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    //Fábrica de consumo e suas configurações
    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        val configs = new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "StringConsumerGroup"); //normalmente o nome da classe do consumidor
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configs);
    }

    //Utilizado para construção de containers com métodos anotados com o @KafkaListener
    //precisa do ConsumerFactory criado pelo metodo acima
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory() {
        val factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }

    
    //ConsumerFactory e ConcurrentKafkaListenerContainerFactory para mesagens com o objeto Greeting
    @Bean
    public ConsumerFactory<String, Greeting> greetingConsumerFactory() {
        val configs = new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "GreetingConsumerGroup");
//        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); //para processar mensagens anteriores a criação do grupo de consumo
		return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new JsonDeserializer<>(Greeting.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Greeting> greetingKafkaListenerContainerFactory() {
        val factory = new ConcurrentKafkaListenerContainerFactory<String, Greeting>();
        factory.setConsumerFactory(greetingConsumerFactory());
        // Você pode criar um consumidor que filtra baseado em um criterio
        // factory.setRecordFilterStrategy(record -> record.value().contains("World"));
        return factory;
    }

}
