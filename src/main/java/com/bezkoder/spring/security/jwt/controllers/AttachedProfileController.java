package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.FileHandle.FileResponse;
import com.bezkoder.spring.security.jwt.FileHandle.FileService;
import com.bezkoder.spring.security.jwt.common.UrlConstants;
import com.bezkoder.spring.security.jwt.payload.request.AttachedProfileRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.AttachedProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstants.HO_SO_DINH_KEM)
public class AttachedProfileController {

    @Autowired
    AttachedProfileService attachedProfileService;

    @Autowired
    FileService fileService;

    @GetMapping(UrlConstants.INFO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity infoAttachedProfile(@RequestParam Long id) {
        return ResponseEntity.ok(attachedProfileService.infoAttachedProfile(id));
    }

    @GetMapping(UrlConstants.THEO_HO_SO_TAM_TRU_ID)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listAttachedProfileByTemporaryResidenceProfileId(@RequestParam Long id) {
        return ResponseEntity.ok(attachedProfileService.listAttachedProfileByTemporaryResidenceProfileId(id));
    }

    @GetMapping(UrlConstants.THEO_HO_SO_CHUNG_ID)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listAttachedProfileByGeneralProfileId(@RequestParam Long id) {
        return ResponseEntity.ok(attachedProfileService.listAttachedProfileByGeneralProfileId(id));
    }

    @GetMapping(UrlConstants.LIST)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity findAttachedProfileById() {
        return ResponseEntity.ok(attachedProfileService.listAttachedProfile());
    }

    @PostMapping(UrlConstants.ADD)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity addAttachedProfile(@ModelAttribute AttachedProfileRequest attachedProfileRequest) {
        Boolean result = attachedProfileService.addAttachedProfile(attachedProfileRequest);
        return result ?
                ResponseEntity.ok(new MessageResponse("Thêm thành công")) :
                ResponseEntity.badRequest().build();
    }

    @PostMapping(UrlConstants.UPLOAD)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity upload(@ModelAttribute MultipartFile file) {
        FileResponse fileResponse = fileService.uploadAttachedFile(file);
        return fileResponse != null ?
                ResponseEntity.ok(fileService.uploadAttachedFile(file)) :
                ResponseEntity.badRequest().build();
    }

    @PutMapping(UrlConstants.UPDATE)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity updateAttachedProfile(@RequestParam Long id, @RequestBody AttachedProfileRequest attachedProfileRequest) {
        Boolean result = attachedProfileService.updateAttachedProfile(id, attachedProfileRequest);
        return result ?
                ResponseEntity.ok(new MessageResponse("Cập nhật thành công")) :
                ResponseEntity.badRequest().build();
    }

    @DeleteMapping(UrlConstants.DELETE)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteAttachedProfile(@RequestParam Long id) {
        Boolean result = attachedProfileService.deleteAttachedProfile(id);
        return result ?
                ResponseEntity.ok(new MessageResponse("Xoá thành công")) :
                ResponseEntity.badRequest().build();
    }

}
