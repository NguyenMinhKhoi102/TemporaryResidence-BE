package com.bezkoder.spring.security.jwt.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {
    private String to;
    private String subject;
    private String text;
    private byte[] attachFile;
    private String attachFileName;

    public Mail(String to, String subject, String text){
        this.to = to;
        this.subject = subject;
        this.text = text;
    }
}
