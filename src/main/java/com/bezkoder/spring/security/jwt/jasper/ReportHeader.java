package com.bezkoder.spring.security.jwt.jasper;

import org.springframework.http.MediaType;
import com.google.common.collect.ImmutableMap;
import org.springframework.util.MimeType;

import java.util.Map;

public class ReportHeader {
    private static final Map<ReportType, MediaType> header = ImmutableMap.<ReportType, MediaType>builder()
            .put(ReportType.PDF, MediaType.APPLICATION_PDF)
            .put(ReportType.DOCX, MediaType.asMediaType(MimeType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document")))
            .put(ReportType.HTML, MediaType.TEXT_HTML)
            .put(ReportType.RTF, MediaType.asMediaType(MimeType.valueOf("application/rtf")))
            .build();

    public static MediaType getHeader(ReportType reportType){
        return header.get(reportType);
    }
}
