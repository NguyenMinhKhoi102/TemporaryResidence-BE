package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.common.UrlConstants;
import com.bezkoder.spring.security.jwt.payload.request.AddWardRequest;
import com.bezkoder.spring.security.jwt.payload.request.UpdateWardRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstants.Xa)
public class WardController {

    @Autowired
    WardService wardService;

    @GetMapping(UrlConstants.INFO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity infoWard(@RequestParam String code){
        return ResponseEntity.ok(wardService.infoWard(code));
    }
    @GetMapping(UrlConstants.TIM_THEO_TEN_DAY_DU)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity infoDistrictByFullName(@RequestParam String fullName, @RequestParam String districtCode){
        return ResponseEntity.ok(wardService.infoWardByFullNameAndDistrictCode(fullName, districtCode));
    }

    @GetMapping(UrlConstants.LIST)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listWard(){
        return ResponseEntity.ok(wardService.listWard());
    }
    @GetMapping(UrlConstants.DANH_SACH_XA_THEO_HUYEN)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listWardByDistrictCode(@RequestParam String districtCode){
        return ResponseEntity.ok(wardService.listWardByDistrictCode(districtCode));
    }

    @PostMapping(UrlConstants.ADD)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity addWard(@RequestBody AddWardRequest request){
        Boolean result = wardService.addWard(request);
        return result ?
                ResponseEntity.ok(new MessageResponse("Thêm thành công")) :
                ResponseEntity.badRequest().build();
    }

    @PutMapping(UrlConstants.UPDATE)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity addWard(@RequestParam String code, @RequestBody UpdateWardRequest request){
        Boolean result = wardService.updateWard(code, request);
        return result ?
                ResponseEntity.ok(new MessageResponse("Cập nhật thành công")) :
                ResponseEntity.badRequest().build();
    }
    @DeleteMapping(UrlConstants.DELETE)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity addWard(@RequestParam String code){
        Boolean result = wardService.deleteWard(code);
        return result ?
                ResponseEntity.ok(new MessageResponse("Xoá thành công")) :
                ResponseEntity.badRequest().build();
    }
}
