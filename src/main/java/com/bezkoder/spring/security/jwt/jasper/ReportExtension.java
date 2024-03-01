package com.bezkoder.spring.security.jwt.jasper;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class ReportExtension {

    public static final Map<ReportType, String> extension = ImmutableMap.<ReportType, String>builder()
            .put(ReportType.PDF, ".pdf")
            .put(ReportType.DOCX, ".docx")
            .put(ReportType.HTML, ".html")
            .put(ReportType.RTF, ".rtf")
            .build();

    public static String getExtension(ReportType reportType){
        return extension.get(reportType);
    }
}
