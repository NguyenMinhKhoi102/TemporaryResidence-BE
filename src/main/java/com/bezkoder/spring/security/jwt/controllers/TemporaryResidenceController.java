package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.common.UrlConstants;
import com.bezkoder.spring.security.jwt.payload.request.AcceptedTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.AdditionalTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.DeniedTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.TemporaryResidenceRegistrationRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.TemporaryResidenceService;
import com.bezkoder.spring.security.jwt.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstants.HO_SO_CHUNG)
public class TemporaryResidenceController {

    @Autowired
    TemporaryResidenceService temporaryResidenceService;

    @PostMapping(UrlConstants.DUYET_HO_SO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity acceptedTemporaryResidence(@RequestParam Long id, @RequestBody AcceptedTemporaryResidenceRequest request) {
        Boolean result = temporaryResidenceService.acceptedTemporaryResidence(id, request);
        return result ?
                ResponseEntity.ok(new MessageResponse("Duyệt thành công")) :
                ResponseEntity.badRequest().body(new MessageResponse("Duyệt thất bại"));
    }

    @PostMapping(UrlConstants.BO_SUNG_HO_SO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity additionalTemporaryResidence(@RequestParam Long id, @RequestBody AdditionalTemporaryResidenceRequest request) {
        Boolean result = temporaryResidenceService.additionalTemporaryResidence(id, request);
        return result ?
                ResponseEntity.ok(new MessageResponse("Yêu cầu bổ sung thành công")) :
                ResponseEntity.badRequest().body(new MessageResponse("Yêu cầu bổ sung thất bại"));
    }

    @PostMapping(UrlConstants.TU_CHOI_HO_SO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity deniedTemporaryResidence(@RequestParam Long id, @RequestBody DeniedTemporaryResidenceRequest request) {
        Boolean result = temporaryResidenceService.deniedTemporaryResidence(id, request);
        return result ?
                ResponseEntity.ok(new MessageResponse("Từ chối thành công")) :
                ResponseEntity.badRequest().body(new MessageResponse("Từ chối thất bại"));
    }

    @PostMapping(UrlConstants.DANG_KY)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity registerTemporaryResidence(@ModelAttribute TemporaryResidenceRegistrationRequest rq, @RequestParam Boolean isCopy) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        System.out.println(rq);
        Boolean result = temporaryResidenceService.registerTemporaryResidence(userId, rq, isCopy);
        return result ?
                ResponseEntity.ok(new MessageResponse("Đăng ký thành công")) :
                ResponseEntity.badRequest().body(new MessageResponse("Đăng ký thất bại"));
    }

    @PostMapping(UrlConstants.GIA_HAN + UrlConstants.TU_CHOI_HO_SO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity deniedExtension(@RequestParam Long id, @RequestBody DeniedTemporaryResidenceRequest request) {
        Boolean result = temporaryResidenceService.deniedExtension(id, request);
        return result ?
                ResponseEntity.ok(new MessageResponse("Từ chối thành công")) :
                ResponseEntity.badRequest().body(new MessageResponse("Từ chối thất bại"));
    }

    @PostMapping(UrlConstants.GIA_HAN + UrlConstants.DUYET_HO_SO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity acceptedExtension(@RequestParam Long id) {
        Boolean result = temporaryResidenceService.acceptedExtension(id);
        return result ?
                ResponseEntity.ok(new MessageResponse("Duyệt thành công")) :
                ResponseEntity.badRequest().body(new MessageResponse("Duyệt thất bại"));
    }

    @PostMapping(UrlConstants.XOA_DANG_KY + UrlConstants.TU_CHOI_HO_SO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity deniedDelete(@RequestParam Long id, @RequestBody DeniedTemporaryResidenceRequest request) {
        Boolean result = temporaryResidenceService.deniedDelete(id, request);
        return result ?
                ResponseEntity.ok(new MessageResponse("Từ chối thành công")) :
                ResponseEntity.badRequest().body(new MessageResponse("Từ chối thất bại"));
    }

    @PostMapping(UrlConstants.XOA_DANG_KY + UrlConstants.DUYET_HO_SO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity acceptedDelete(@RequestParam Long id) {
        Boolean result = temporaryResidenceService.acceptedDelete(id);
        return result ?
                ResponseEntity.ok(new MessageResponse("Duyệt thành công")) :
                ResponseEntity.badRequest().body(new MessageResponse("Duyệt thất bại"));
    }


}
