package com.kadiraksoy.mailservice.dto;

import java.util.List;

public class EmailResponseDto {
    private List<String> emails;
    private String message;

    public EmailResponseDto(List<String> emails, String message) {
        this.emails = emails;
        this.message = message;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
