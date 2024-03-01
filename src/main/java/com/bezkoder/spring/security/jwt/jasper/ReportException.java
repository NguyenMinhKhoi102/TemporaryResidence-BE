package com.bezkoder.spring.security.jwt.jasper;

public class ReportException extends RuntimeException{
    public ReportException(String message){
        super(message);
    }
    public ReportException(Exception e){
        super(e);
    }
}
