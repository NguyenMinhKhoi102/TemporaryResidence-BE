package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.TypeNotification;
import com.bezkoder.spring.security.jwt.payload.request.TypeNotificationRequest;
import com.bezkoder.spring.security.jwt.repository.TypeNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeNotificationServiceImpl implements TypeNotificationService{
    @Autowired
    TypeNotificationRepository typeNotificationRepository;
    @Override
    public TypeNotification infoTypeNotification(Integer id){
        return typeNotificationRepository.findByIdAndIsDelete(id, 0).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
    }
    @Override
    public List<TypeNotification> listTypeNotification(Boolean active){
        return active ?
                typeNotificationRepository.findAllByIsDeleteAndIsActive(0, 1) :
                typeNotificationRepository.findAllByIsDelete(0);
    }
    @Override
    public Boolean addTypeNotification(TypeNotificationRequest typeNotificationRequest){
        try{
            TypeNotification typeNotification = TypeNotification.builder()
                    .name(typeNotificationRequest.getName())
                    .isActive(1)
                    .isDelete(0)
                    .build();
            typeNotificationRepository.save(typeNotification);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean updateTypeNotification(Integer id, TypeNotificationRequest typeNotificationRequest){
        try{
            TypeNotification typeNotification = typeNotificationRepository.findByIdAndIsDelete(id, 0).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            typeNotification.setName(typeNotificationRequest.getName() == null ? typeNotification.getName() : typeNotificationRequest.getName());
            typeNotification.setIsActive(typeNotificationRequest.getIsActive() == null ? typeNotification.getIsActive() : typeNotificationRequest.getIsActive());
            typeNotificationRepository.save(typeNotification);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean deleteTypeNotification(Integer id){
        try{
            TypeNotification typeNotification = typeNotificationRepository.findByIdAndIsDelete(id, 0).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            typeNotification.setIsDelete(1);
            typeNotificationRepository.save(typeNotification);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
