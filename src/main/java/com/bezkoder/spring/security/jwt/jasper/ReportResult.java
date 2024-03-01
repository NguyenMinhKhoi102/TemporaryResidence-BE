package com.bezkoder.spring.security.jwt.jasper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResult {
    private String name;
    private byte[] fileByte;
    private ReportType type;
}
