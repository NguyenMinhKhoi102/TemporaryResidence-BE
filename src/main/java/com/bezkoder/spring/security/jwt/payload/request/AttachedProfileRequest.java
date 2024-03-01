package com.bezkoder.spring.security.jwt.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachedProfileRequest {
    private String name;
    private String note;
    private Integer isCopy;
    private MultipartFile file;
    private Long generalProfileId;
    private Long temporaryResidenceProfileId;
}
