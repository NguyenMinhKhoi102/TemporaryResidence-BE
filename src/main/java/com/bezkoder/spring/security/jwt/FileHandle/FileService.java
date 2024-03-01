package com.bezkoder.spring.security.jwt.FileHandle;

import com.bezkoder.spring.security.jwt.jasper.ReportType;
import com.bezkoder.spring.security.jwt.payload.request.AcceptedTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.AdditionalTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.DeniedTemporaryResidenceRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    Boolean uploadAttachedFile(MultipartFile file, String path);
    FileResponse uploadAttachedFile(MultipartFile file);
    Boolean uploadAvatar(MultipartFile file, String path);
    ResponseEntity CT01Response(Long generalProfileId, ReportType reportType);
    ResponseEntity CT01ResponsePDF(Long generalProfileId, ReportType reportType) throws Exception;
    ResponseEntity CT04Response(Long generalProfileId, AcceptedTemporaryResidenceRequest request, ReportType reportType);
    ResponseEntity CT05Response(Long generalProfileId, AdditionalTemporaryResidenceRequest request, ReportType reportType);
    ResponseEntity CT06Response(Long generalProfileId, DeniedTemporaryResidenceRequest request, ReportType reportType);
}
