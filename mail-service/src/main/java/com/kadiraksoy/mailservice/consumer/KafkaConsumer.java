package com.kadiraksoy.mailservice.consumer;


import com.kadiraksoy.mailservice.dto.UserDto;
import com.kadiraksoy.mailservice.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    private final EmailService emailService;

    public KafkaConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "mail-topic", groupId = "mail-consumer-group")
    public void consumeScriptData(UserDto userDto) throws MessagingException {
        String email = userDto.getEmail();
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();

        log.info("Alınan veriler: email:" + email +
                "firstName:" + firstName +
                "lastName" + lastName);
        emailService.sendMailKafka(email,firstName,lastName);
        log.info("Gönderilen veriler: email:" + email +
                "firstName:" + firstName +
                "lastName" + lastName);
    }
}
