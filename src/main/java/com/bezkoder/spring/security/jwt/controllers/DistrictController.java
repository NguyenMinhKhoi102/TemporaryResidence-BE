package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.common.UrlConstants;
import com.bezkoder.spring.security.jwt.payload.request.AddDistrictRequest;
import com.bezkoder.spring.security.jwt.payload.request.UpdateDistrictRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstants.Huyen)
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @GetMapping(UrlConstants.INFO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity infoDistrict(@RequestParam String code){
        return ResponseEntity.ok(districtService.infoDistrict(code));
    }
    @GetMapping(UrlConstants.TIM_THEO_TEN_DAY_DU)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity infoDistrictByFullName(@RequestParam String fullName, @RequestParam String provinceCode){
        return ResponseEntity.ok(districtService.infoDistrictByFullNameAndProvinceCode(fullName, provinceCode));
    }
    @GetMapping(UrlConstants.LIST)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listDistrict(){
        return ResponseEntity.ok(districtService.listDistrict());
    }
    @GetMapping(UrlConstants.DANH_SACH_HUYEN_THEO_TINH)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listDistrictByProvinceCode(@RequestParam String provinceCode){
        return ResponseEntity.ok(districtService.listDistrictByProvinceCode(provinceCode));
    }

    @PostMapping(UrlConstants.ADD)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity addDistrict(@RequestBody AddDistrictRequest request){
        Boolean result = districtService.addDistrict(request);
        return result ?
                ResponseEntity.ok(new MessageResponse("Thêm thành công")) :
                ResponseEntity.badRequest().build();
    }

    @PutMapping(UrlConstants.UPDATE)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity addDistrict(@RequestParam String code, @RequestBody UpdateDistrictRequest request){
        Boolean result = districtService.updateDistrict(code, request);
        return result ?
                ResponseEntity.ok(new MessageResponse("Cập nhật thành công")) :
                ResponseEntity.badRequest().build();
    }
    @DeleteMapping(UrlConstants.DELETE)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity addDistrict(@RequestParam String code){
        Boolean result = districtService.deleteDistrict(code);
        return result ?
                ResponseEntity.ok(new MessageResponse("Xoá thành công")) :
                ResponseEntity.badRequest().build();
    }

}
