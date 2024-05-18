package com.kadiraksoy.mailservice.service;



import com.kadiraksoy.mailservice.dto.EmailResponseDto;
import com.kadiraksoy.mailservice.dto.UserDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final APIClient apiClient;

    public EmailService(JavaMailSender javaMailSender, APIClient apiClient) {
        this.javaMailSender = javaMailSender;
        this.apiClient = apiClient;
    }


    public void sendMail(String email,String firstName, String lastName, String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Welcome" + firstName + lastName );
        mimeMessageHelper.setText("<div>" + message + "</div>", true);

        javaMailSender.send(mimeMessage);
        log.info("mail gönderildi.");

//        String email2 =email;
//        String firstName2 = firstName;
//        String lastName2 = lastName;
//
//        System.out.println(email2 + firstName2 + lastName2);
    }

    public EmailResponseDto sendMailAllUsers(String message) {
        List<UserDto> userDtoList = apiClient.getAllUsers();
        System.out.println(userDtoList);

        List<String> emailList = new ArrayList<>();
        for (UserDto user : userDtoList) {
            try {
                sendMail(user.getEmail(), user.getFirstName(), user.getLastName(), message);
                emailList.add(user.getEmail());
            } catch (MessagingException e) {

                System.err.println("Mail gönderilirken bir hata oluştu: " + user.getEmail());

            }
        }
        return new EmailResponseDto(emailList, message);
    }


}
