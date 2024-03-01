package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.common.UrlConstants;
import com.bezkoder.spring.security.jwt.payload.request.TemporaryResidenceProfileRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.TemporaryResidenceProfileService;
import com.bezkoder.spring.security.jwt.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstants.HO_SO_TAM_TRU)
public class TemporaryResidenceProfileController {

    @Autowired
    TemporaryResidenceProfileService temporaryResidenceProfileService;

    @GetMapping(UrlConstants.INFO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity infoTemporaryResidenceProfile(@RequestParam Long id) {
        return ResponseEntity.ok(temporaryResidenceProfileService.infoTemporaryResidenceProfile(id));
    }

    @GetMapping(UrlConstants.LIST)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listTemporaryResidenceProfiles() {
        return ResponseEntity.ok(temporaryResidenceProfileService.listTemporaryResidenceProfiles());
    }

    @GetMapping(UrlConstants.THEO_CON_HAN)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listTemporaryResidenceProfilesByIsExpired(@RequestParam Integer isExpired) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        return ResponseEntity.ok(temporaryResidenceProfileService.listTemporaryResidenceProfilesByIsExpired(isExpired, userId));
    }

    @GetMapping(UrlConstants.THEO_MA_XA)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listTemporaryResidenceProfilesByWardCode(@RequestParam String wardCode) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        return ResponseEntity.ok(temporaryResidenceProfileService.listTemporaryResidenceProfilesByWardCode(wardCode, userId));
    }
    @GetMapping(UrlConstants.LIST + UrlConstants.THEO_MA_XA)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listTemporaryResidenceProfilesByWardCode2(@RequestParam String wardCode) {
        return ResponseEntity.ok(temporaryResidenceProfileService.listTemporaryResidenceProfilesByWardCode2(wardCode));
    }

    @PostMapping(UrlConstants.ADD)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity addTemporaryResidenceProfiles(@RequestBody TemporaryResidenceProfileRequest temporaryResidenceProfileRequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        Boolean result = temporaryResidenceProfileService.addTemporaryResidenceProfile(temporaryResidenceProfileRequest, userId);
        return result ?
                ResponseEntity.ok(new MessageResponse("Thêm thành công")) :
                ResponseEntity.badRequest().build();
    }

    @PutMapping(UrlConstants.UPDATE)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity updateTemporaryResidenceProfiles(@RequestParam Long id, @RequestBody TemporaryResidenceProfileRequest temporaryResidenceProfileRequest) {
        System.out.println(123);
        Boolean result = temporaryResidenceProfileService.updateTemporaryResidenceProfile(id, temporaryResidenceProfileRequest);
        return result ?
                ResponseEntity.ok(new MessageResponse("Cập nhật thành công")) :
                ResponseEntity.badRequest().build();
    }

    @DeleteMapping(UrlConstants.DELETE)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity deleteTemporaryResidenceProfiles(@RequestParam Long id) {
        Boolean result = temporaryResidenceProfileService.deleteTemporaryResidenceProfile(id);
        return result ?
                ResponseEntity.ok(new MessageResponse("Xoá thành công")) :
                ResponseEntity.badRequest().build();
    }
}
