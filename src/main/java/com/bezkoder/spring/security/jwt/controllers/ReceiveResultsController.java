package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.common.UrlConstants;
import com.bezkoder.spring.security.jwt.payload.request.ReceiveResultRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.repository.ReceiveResultsRepository;
import com.bezkoder.spring.security.jwt.security.services.ReceiveResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstants.HINH_THUC_NHAN_KET_QUA)
public class ReceiveResultsController {

    @Autowired
    ReceiveResultsRepository receiveResultsRepository;

    @Autowired
    ReceiveResultService receiveResultService;

    @GetMapping(UrlConstants.INFO)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity infoReceiveResult(@RequestParam Integer id) {
        return ResponseEntity.ok(receiveResultService.infoReceiveResult(id));

    }

    @GetMapping(UrlConstants.LIST)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity listReceiveResult(@RequestParam Boolean active) {
        return ResponseEntity.ok(receiveResultService.listReceiveResults(active));
    }

    @PostMapping(UrlConstants.ADD)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addReceiveResult(@RequestBody ReceiveResultRequest receiveResultRequest) {
        if (receiveResultsRepository.existsByName(receiveResultRequest.getName()))
            return ResponseEntity.badRequest().body(new MessageResponse("Tên đã tồn tại"));
        Boolean result = receiveResultService.addReceiveResults(receiveResultRequest);
        return result ?
                ResponseEntity.ok(new MessageResponse("Thêm thành công")) :
                ResponseEntity.badRequest().build();
    }

    @PutMapping(UrlConstants.UPDATE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateReceiveResult(@RequestParam Integer id, @RequestBody ReceiveResultRequest receiveResultRequest) {
        Boolean result = receiveResultService.updateReceiveResults(id, receiveResultRequest);
        return result ?
                ResponseEntity.ok(new MessageResponse("Cập nhật thành công")) :
                ResponseEntity.badRequest().build();
    }

    @DeleteMapping(UrlConstants.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteReceiveResult(@RequestParam Integer id) {
        Boolean result = receiveResultService.deleteReceiveResults(id);
        return result ?
                ResponseEntity.ok(new MessageResponse("Xoá thành công")) :
                ResponseEntity.badRequest().build();
    }


}
