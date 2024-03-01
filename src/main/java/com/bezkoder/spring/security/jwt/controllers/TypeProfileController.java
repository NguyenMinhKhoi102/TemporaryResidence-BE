package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.common.UrlConstants;
import com.bezkoder.spring.security.jwt.payload.request.TypeProfileRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.repository.TypeProfileRepository;
import com.bezkoder.spring.security.jwt.security.services.TypeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstants.LOAI_HO_SO)
public class TypeProfileController {

    @Autowired
    TypeProfileRepository typeProfileRepository;

    @Autowired
    TypeProfileService typeProfileService;

    @GetMapping(UrlConstants.INFO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity infoTypeProfile(@RequestParam Integer id) {
        return ResponseEntity.ok(typeProfileService.infoTypeProfile(id));
    }

    @GetMapping(UrlConstants.LIST)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity allTypeProfiles(@RequestParam Boolean active) {
        return ResponseEntity.ok(typeProfileService.listTypeProfile(active));
    }


    @PostMapping(UrlConstants.ADD)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addTypeProfile(@RequestBody TypeProfileRequest typeProfileRequest) {
            if (typeProfileRepository.existsByName(typeProfileRequest.getName()))
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Tên đã tồn tại"));
            Boolean result = typeProfileService.addTypeProfile(typeProfileRequest);
            return result ?
                    ResponseEntity.ok(new MessageResponse("Thêm thành công")) :
                    ResponseEntity.badRequest().build();
    }

    @PutMapping(UrlConstants.UPDATE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateTypeProfile(@RequestParam Integer id, @RequestBody TypeProfileRequest typeProfileRequest) {
        Boolean result = typeProfileService.updateTypeProfile(id, typeProfileRequest);
        return result ?
                ResponseEntity.ok(new MessageResponse("Cập nhật thành công")) :
                ResponseEntity.badRequest().build();
    }

    @DeleteMapping(UrlConstants.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteTypeProfile(@RequestParam Integer id) {
        Boolean result = typeProfileService.deleteTypeProfile(id);
        return result ?
                ResponseEntity.ok(new MessageResponse("Xoá thành công")) :
                ResponseEntity.badRequest().build();
    }
}
