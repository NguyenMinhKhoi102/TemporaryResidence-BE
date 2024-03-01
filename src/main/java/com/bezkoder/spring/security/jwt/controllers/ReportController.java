package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.FileHandle.FileService;
import com.bezkoder.spring.security.jwt.common.UrlConstants;
import com.bezkoder.spring.security.jwt.jasper.ReportType;
import com.bezkoder.spring.security.jwt.payload.request.AcceptedTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.AdditionalTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.DeniedTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.GetAttachFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstants.XUAT_THEO_MAU)
public class ReportController {

    @Autowired
    FileService fileService;

    @PostMapping(UrlConstants.MAU_CT01)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity exportCT01(@RequestParam Long generalProfileId, @RequestParam Boolean type) throws Exception{
        ReportType reportType = type ? ReportType.RTF : ReportType.PDF;
        return fileService.CT01Response(generalProfileId, reportType);
    }

    @PostMapping(UrlConstants.SAMPLE_CT01)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity getCT01() throws Exception{
        Resource resource = new ClassPathResource("templates/CT01_sample.doc");
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sample.doc");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
    @PostMapping("/download-attached-file")
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity downloadAttachFile(@RequestBody GetAttachFileRequest rq) throws Exception{
        String name = "uploads/attached/" + rq.getFileName();
        System.out.println(name);
        Resource resource = new ClassPathResource(name);
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sample.doc");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @PostMapping(UrlConstants.MAU_CT04)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity exportCT04(@RequestParam Long generalProfileId, @RequestParam Boolean type, @RequestBody AcceptedTemporaryResidenceRequest request){
        ReportType reportType = type ? ReportType.DOCX : ReportType.PDF;
        return fileService.CT04Response(generalProfileId,request, reportType);
    }
    @PostMapping(UrlConstants.MAU_CT05)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity exportCT05(@RequestParam Long generalProfileId, @RequestParam Boolean type, @RequestBody AdditionalTemporaryResidenceRequest request){
        ReportType reportType = type ? ReportType.DOCX : ReportType.PDF;
        return fileService.CT05Response(generalProfileId,request, reportType);
    }
    @PostMapping(UrlConstants.MAU_CT06)
    @PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
    public ResponseEntity exportCT06(@RequestParam Long generalProfileId, @RequestParam Boolean type, @RequestBody DeniedTemporaryResidenceRequest request){
        ReportType reportType = type ? ReportType.DOCX : ReportType.PDF;
        return fileService.CT06Response(generalProfileId,request, reportType);
    }
}
