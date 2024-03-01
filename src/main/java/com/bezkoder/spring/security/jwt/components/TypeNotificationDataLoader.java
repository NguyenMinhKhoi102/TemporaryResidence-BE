package com.bezkoder.spring.security.jwt.components;

import com.bezkoder.spring.security.jwt.models.TypeNotification;
import com.bezkoder.spring.security.jwt.repository.TypeNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class TypeNotificationDataLoader implements CommandLineRunner {

    final private TypeNotificationRepository typeNotificationRepository;

    @Autowired
    public TypeNotificationDataLoader(TypeNotificationRepository typeNotificationRepository){
        this.typeNotificationRepository = typeNotificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = typeNotificationRepository.count();

        if(count == 0){
            TypeNotification typeNotification = new TypeNotification("Qua email");
            TypeNotification typeNotification1 = new TypeNotification("Qua cổng tạm trú");
            typeNotificationRepository.save(typeNotification);
            typeNotificationRepository.save(typeNotification1);
        }

    }
}
