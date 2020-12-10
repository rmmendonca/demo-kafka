package com.example.demo;

import lombok.val;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;

@Configuration
public class KafkaTopicConfig {
	
	/*
	 * Classe para configurar a criação de novos tópicos, são criados na
	 * inicialização, recomendado.
	 * 
	 * Caso um tópico não seja explicitamente criado, quando for enviada
	 * uma mensagem para ele, o tópico é criado automaticamente com 1
	 * partição e com fator de replicação = 1.
	 * 
	 * -particoes : numero de partições em que o tópico é "espalhado" entre
	 * diferentes kafka brokers
	 * sobre tunning de particionamento de topicos
	 * https://docs.cloudera.com/runtime/7.2.1/kafka-performance-tuning/topics/kafka-tune-sizing-partition-number.html
	 * 
	 * -fator de replicação: copia dos dados em multiplos hosts (a documentação 
	 * indica 3 em ambiente de produção)
	 */

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        val configs = new HashMap<String, Object>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    
    //criacao de tópico
    @Bean
    public NewTopic topic1() {
        return new NewTopic("topico.coiso", 5, (short) 1);
    }
    
    @Bean
    public NewTopic topic2() {
        return new NewTopic("topico.coiso.string", 5, (short) 1);
    }

}
