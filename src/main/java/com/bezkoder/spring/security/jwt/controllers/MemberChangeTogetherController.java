package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.common.UrlConstants;
import com.bezkoder.spring.security.jwt.payload.request.MemberChangeTogetherRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.repository.MemberChangeTogetherRepository;
import com.bezkoder.spring.security.jwt.security.services.MemberChangeTogetherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstants.THANH_VIEN_CUNG_THAY_DOI)
public class MemberChangeTogetherController {

    @Autowired
    MemberChangeTogetherRepository memberChangeTogetherRepository;

    @Autowired
    MemberChangeTogetherService memberChangeTogetherService;

    @GetMapping(UrlConstants.INFO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity infoMemberChangeTogether(@RequestParam Long id) {
        return ResponseEntity.ok(memberChangeTogetherService.infoMemberChangeTogether(id));
    }

    @GetMapping(UrlConstants.LIST)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listMemberChangeTogether() {
        return ResponseEntity.ok(memberChangeTogetherService.listMembersChangeTogether());
    }

    @GetMapping(UrlConstants.THEO_HO_SO_CHUNG_ID)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listMemberChangeTogether(@RequestParam Long id) {
        return ResponseEntity.ok(memberChangeTogetherService.listMembersChangeTogetherByGeneralProfileId(id));
    }


    @PostMapping(UrlConstants.ADD)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity addMemberChangeTogether(@RequestBody MemberChangeTogetherRequest memberChangeTogetherRequest){
        if(memberChangeTogetherRepository.existsByCmndCccdAndIsDeleteAndGeneralProfileId(memberChangeTogetherRequest.getCmndCccd(), 0, memberChangeTogetherRequest.getGeneralProfileId()))
            return ResponseEntity.badRequest().body(new MessageResponse("Số ĐDCN(CCCD)/CMND không thể trùng"));
        Boolean result = memberChangeTogetherService.addMemberChangeTogether(memberChangeTogetherRequest);
        return result ?
                ResponseEntity.ok(new MessageResponse("Thêm thành công")):
                ResponseEntity.badRequest().build();
    }

    @PutMapping(UrlConstants.UPDATE)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity updateMemberChangeTogether(@RequestParam Long id, @RequestBody MemberChangeTogetherRequest memberChangeTogetherRequest){
        Boolean result = memberChangeTogetherService.updateMemberChangeTogether(id, memberChangeTogetherRequest);
        return result ?
                ResponseEntity.ok(new MessageResponse("Cập nhật thành công")):
                ResponseEntity.badRequest().build();
    }

    @DeleteMapping(UrlConstants.DELETE)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity deleteMemberChangeTogether(@RequestParam Long id){
        Boolean result = memberChangeTogetherService.deleteMemberChangeTogether(id);
        return result ?
                ResponseEntity.ok(new MessageResponse("Xoá thành công")):
                ResponseEntity.badRequest().build();
    }
}
