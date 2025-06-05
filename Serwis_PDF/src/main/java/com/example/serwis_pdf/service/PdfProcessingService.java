package com.example.serwis_pdf.service;

import com.example.serwis_pdf.model.PersonData;
import com.example.serwis_pdf.util.PdfTypeDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class PdfProcessingService {

    @Value("${excel.file-path}")
    private String excelFilePath;

    @Autowired
    private TextExtractor textExtractor;
    @Autowired private OcrExtractor ocrExtractor;
    @Autowired private ExcelWriterService excelWriterService;

    public List<PersonData> processPdf(MultipartFile file, String excelPath) throws Exception {
        File convFile = convertMultiPartToFile(file);
        String text;
        if (PdfTypeDetector.isTextBasedPdf(convFile)) {
            text = textExtractor.extractText(convFile);
        } else {
            text = ocrExtractor.extractTextFromScan(convFile);
        }
        List<PersonData> people = textExtractor.extractPersonData(text);
        excelWriterService.saveToExistingExcel(people, excelPath);
        return people;
    }

    private File convertMultiPartToFile(MultipartFile file) throws Exception {
        File convFile = File.createTempFile("uploaded", ".pdf");
        try (FileOutputStream fos = new FileOutputStream(convFile); InputStream is = file.getInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        return convFile;
    }
}
