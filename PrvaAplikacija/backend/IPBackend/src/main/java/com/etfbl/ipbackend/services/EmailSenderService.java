package com.etfbl.ipbackend.services;

public interface EmailSenderService {

    void sendEmail(String toEmail, String subject, String body);
}
