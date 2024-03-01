package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.TypeNotification;
import com.bezkoder.spring.security.jwt.payload.request.TypeNotificationRequest;
import com.bezkoder.spring.security.jwt.repository.TypeNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TypeNotificationService {

    TypeNotification infoTypeNotification(Integer id);
    List<TypeNotification> listTypeNotification(Boolean active);
    Boolean addTypeNotification(TypeNotificationRequest typeNotificationRequest);
    Boolean updateTypeNotification(Integer id, TypeNotificationRequest typeNotificationRequest);
    Boolean deleteTypeNotification(Integer id);

}
