package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.common.UrlConstants;
import com.bezkoder.spring.security.jwt.payload.request.TypeNotificationRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.repository.TypeNotificationRepository;
import com.bezkoder.spring.security.jwt.security.services.TypeNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstants.LOAI_THONG_BAO)
public class TypeNotificationController {

    @Autowired
    TypeNotificationRepository typeNotificationRepository;

    @Autowired
    TypeNotificationService typeNotificationService;

    @GetMapping(UrlConstants.INFO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity infoTypeNotifications(@RequestParam Integer id){
        return ResponseEntity.ok(typeNotificationService.infoTypeNotification(id));
    }

    @GetMapping(UrlConstants.LIST)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listTypeNotifications(@RequestParam Boolean active){
        return ResponseEntity.ok(typeNotificationService.listTypeNotification(active));
    }

    @PostMapping(UrlConstants.ADD)
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity addTypeNotification(@RequestBody TypeNotificationRequest typeNotificationRequest){
            if(typeNotificationRepository.existsByName(typeNotificationRequest.getName()))
                return ResponseEntity.ok(new MessageResponse("Tên loại đã tồn tại"));
            Boolean result = typeNotificationService.addTypeNotification(typeNotificationRequest);
            return result ?
                    ResponseEntity.ok(new MessageResponse("Thêm thành công")) :
                    ResponseEntity.badRequest().build();
    }

    @PutMapping(UrlConstants.UPDATE)
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity updateTypeNotification(@RequestParam Integer id, @RequestBody TypeNotificationRequest typeNotificationRequest){
        Boolean result = typeNotificationService.updateTypeNotification(id, typeNotificationRequest);
        return result ?
                ResponseEntity.ok(new MessageResponse("Cập nhật thành công")) :
                ResponseEntity.badRequest().build();
    }

    @DeleteMapping(UrlConstants.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity deleteTypeNotification(@RequestParam Integer id){
        Boolean result = typeNotificationService.deleteTypeNotification(id);
        return result ?
                ResponseEntity.ok(new MessageResponse("Xoá thành công")) :
                ResponseEntity.badRequest().build();
    }
}
