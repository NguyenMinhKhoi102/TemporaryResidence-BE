package com.bezkoder.spring.security.jwt.jasper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportListResult {
    private String name;
    private List<byte[]> filesByte;
    private ReportType type;
}
