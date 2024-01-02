package com.paung.compo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class SendingMessageBean implements ApplicationRunner{

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "test-kafka", groupId = "group-id")
    public void listener(String msg){
        // pecinta lombok brokk
        System.out.println("pesan diterima, message: " + msg);
    }

    public void sendMessage(String msg){
        kafkaTemplate.send("test-kafka", msg);
    }

    @Override
    public void run(ApplicationArguments args0) throws Exception {
        sendMessage("Hi Kafka !");
    }

}
