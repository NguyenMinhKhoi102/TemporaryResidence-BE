package com.bezkoder.spring.security.jwt.jasper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
public class TestUtils {
    public static byte[] convertWordBytesToPdfBytes(byte[] wordBytes) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(wordBytes);
        XWPFDocument document = new XWPFDocument(inputStream);
        Document pdfDocument = new Document();
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(pdfDocument, pdfOutputStream);
        pdfDocument.open();

        for (XWPFPictureData picture : document.getAllPictures()) {
            byte[] bytes = picture.getData();
            // Process images here if needed
        }

        for (XWPFParagraph paragraph : document.getParagraphs()) {
            pdfDocument.add(new PdfPTable(new float[]{1f})
                    .addCell(new PdfPCell(new com.itextpdf.text.Paragraph(paragraph.getText()))));
        }

        pdfDocument.close();
        inputStream.close();

        return pdfOutputStream.toByteArray();
    }
}
