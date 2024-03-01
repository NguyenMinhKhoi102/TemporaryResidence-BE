package com.bezkoder.spring.security.jwt.payload.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetAttachFileRequest {
    private String fileName;
}
