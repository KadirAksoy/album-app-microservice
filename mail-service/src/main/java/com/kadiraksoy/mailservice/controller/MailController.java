package com.kadiraksoy.mailservice.controller;


import com.kadiraksoy.mailservice.dto.EmailResponseDto;
import com.kadiraksoy.mailservice.dto.UserDto;
import com.kadiraksoy.mailservice.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mail")
public class MailController {


    private final EmailService emailService;

    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendAll")
    public EmailResponseDto sendMailToAllUsers(@RequestParam String message) {
        return emailService.sendMailAllUsers(message);
    }
}
