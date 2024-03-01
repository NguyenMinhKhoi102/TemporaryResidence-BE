package com.bezkoder.spring.security.jwt.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdditionalTemporaryResidenceRequest {
    private String additionalFile;
    private String declareAgain;
    private String otherInstructions;
    private String phoneOrgan;
    private String reason;
}
