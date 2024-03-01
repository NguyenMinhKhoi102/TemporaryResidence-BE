package com.bezkoder.spring.security.jwt.jasper;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
@Service
public class ReportUtils {

    private static final ReportException NOT_SUPPORT = new ReportException("Không hỗ trợ xuất hồ sơ theo loại này");
    private static final List<ReportType> OUTPUT_STREAM_EXPORTER_TYPE = Arrays.asList(ReportType.PDF, ReportType.DOCX);
    private static final List<ReportType> HTML_EXPORTER_TYPES = Collections.singletonList(ReportType.HTML);
    private static final List<ReportType> RTF_EXPORTER_TYPES = Collections.singletonList(ReportType.RTF);
    private static final Map<ReportType, JRAbstractExporter> JR_ABSTRACT_EXPORTER_MAP = new HashMap<>();

    static {
        JR_ABSTRACT_EXPORTER_MAP.put(ReportType.PDF, new JRPdfExporter());
        JR_ABSTRACT_EXPORTER_MAP.put(ReportType.DOCX, new JRDocxExporter());
        JR_ABSTRACT_EXPORTER_MAP.put(ReportType.HTML, new HtmlExporter());
        JR_ABSTRACT_EXPORTER_MAP.put(ReportType.RTF, new JRRtfExporter());
    }
    private static ExporterOutput generateExporterOutput(ByteArrayOutputStream byteArrayOutputStream, ReportType reportType){
        ExporterOutput exporterOutput = null;
        if(OUTPUT_STREAM_EXPORTER_TYPE.contains(reportType))
            exporterOutput = new SimpleOutputStreamExporterOutput(byteArrayOutputStream);
        if(HTML_EXPORTER_TYPES.contains(reportType))
            exporterOutput = new SimpleHtmlExporterOutput(byteArrayOutputStream);
        if(RTF_EXPORTER_TYPES.contains(reportType))
            exporterOutput = new SimpleWriterExporterOutput(byteArrayOutputStream);
        if(exporterOutput == null)
            throw NOT_SUPPORT;
        return exporterOutput;
    }

    private static String generateFile(String jasperName, ReportType reportType) {
        String extension = ReportExtension.extension.get(reportType);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(now);
        String reportFileName = timestamp + "_" + jasperName + extension;
        return reportFileName;
    }

    private static JRDataSource generateJRDataSource(List dataSource) {
        if (dataSource == null || dataSource.isEmpty())
            return new JRBeanCollectionDataSource(Collections.emptyList());
        else if (dataSource.get(0) instanceof Map)
            return new JRBeanCollectionDataSource(dataSource);
        else
            return new JRBeanCollectionDataSource(dataSource, false);
    }

    public static ReportResult report(String resourcePath, Map<String, Object> parameters, List dataSource, ReportType reportType){
        try{
            JRDataSource jrDataSource = generateJRDataSource(dataSource);
            ClassPathResource classPathResource = new ClassPathResource(resourcePath);
            InputStream inputStream = classPathResource.getInputStream();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

            String fileName = generateFile(FilenameUtils.removeExtension(classPathResource.getFilename()), reportType);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JRAbstractExporter jrAbstractExporter = JR_ABSTRACT_EXPORTER_MAP.get(reportType);
            ExporterOutput exporterOutput = generateExporterOutput(byteArrayOutputStream, reportType);
            ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);

            jrAbstractExporter.setExporterInput(exporterInput);
            jrAbstractExporter.setExporterOutput(exporterOutput);

            jrAbstractExporter.exportReport();

            byte[] data = byteArrayOutputStream.toByteArray();

            byteArrayOutputStream.close();

            return new ReportResult(fileName, data, reportType);
        }catch (Exception e){
            throw new ReportException(e);
        }
    }

    private static ResponseEntity setHeaderResponse(ReportResult reportResult) {
        try {
            byte[] data = reportResult.getFileByte();
            return ResponseEntity.ok()
                    .headers(headers -> {
                                ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                                        .filename(reportResult.getName())
                                        .build();
                                headers.setContentLength(data.length);
                                headers.setContentDisposition(contentDisposition);
                                headers.setContentType(ReportHeader.getHeader(reportResult.getType()));
                            }
                    ).body(new ByteArrayResource(data));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    public static ResponseEntity downloadReport(String path, Map<String, Object> parameters, List dataSource, ReportType reportType){
        ReportResult reportResult = report(path, parameters, dataSource, reportType);
        return  setHeaderResponse(reportResult);
    }

    public static ResponseEntity downloadReport2(String path, Map<String, Object> parameters, List dataSource, ReportType reportType) throws Exception{
        ReportResult reportResult = report(path, parameters, dataSource, reportType);
        System.out.println(1);
        reportResult.setName(generateFile(reportResult.getName(), ReportType.PDF));
        System.out.println(2);
        byte[] temp = TestUtils.convertWordBytesToPdfBytes(reportResult.getFileByte());
        System.out.println(7);
        reportResult.setFileByte(temp);
        System.out.println(3);
        reportResult.setType(ReportType.PDF);
        System.out.println(4);
        System.out.println(reportResult.getName());
        System.out.println(5);
        return  setHeaderResponse(reportResult);
    }
}
