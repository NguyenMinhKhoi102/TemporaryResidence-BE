package com.bezkoder.spring.security.jwt.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


public interface MailService {
    void sendEmail(Mail mail) throws MessagingException;
    void sendEmailWithAttachment(Mail mail) throws MessagingException;
}
