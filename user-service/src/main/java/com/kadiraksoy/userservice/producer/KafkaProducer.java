package com.kadiraksoy.userservice.producer;


import com.kadiraksoy.userservice.dto.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC_NAME = "mail-topic";

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceData(UserRequest userRequest) {
        kafkaTemplate.send(TOPIC_NAME,userRequest);
        log.info("İşlem başarılı user bilgileri gönderildi.");
    }
}
