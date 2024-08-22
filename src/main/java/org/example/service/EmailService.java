package org.example.service;

public interface EmailService {

    /**
     * send email
     * @param to receiver
     * @param subject email subject
     * @param text email content
     */
    void sendEmail(String to, String subject, String text);
}
