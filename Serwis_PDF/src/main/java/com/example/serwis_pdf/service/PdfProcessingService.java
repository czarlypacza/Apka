package com.example.serwis_pdf.service;

import com.example.serwis_pdf.component.OpenAiClient;
import com.example.serwis_pdf.model.PersonData;
import com.example.serwis_pdf.model.PersonDataExtended;
import com.example.serwis_pdf.util.PdfTypeDetector;
import com.example.serwis_pdf.util.PromptLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PdfProcessingService {

    @Value("${excel.file-path}")
    private String excelFilePath;

    @Autowired
    private TextExtractor textExtractor;
    @Autowired private OcrExtractor ocrExtractor;
    @Autowired private ExcelWriterService excelWriterService;
    @Autowired private OpenAiClient openAiClient;
    @Autowired private PromptLoader promptLoader;

    public List<PersonData> processPdf(MultipartFile file, String excelPath) throws Exception {
        File convFile = convertMultiPartToFile(file);
        String text;
        if (PdfTypeDetector.isTextBasedPdf(convFile)) {
            text = textExtractor.extractText(convFile);
        } else {
            text = ocrExtractor.extractTextFromScan(convFile);
        }
        String prompt1 = promptLoader.getPrompt("persondata");
        String prompt2 = promptLoader.getPrompt("persondataextended");
        List<PersonData> results1 = openAiClient.extractData(text, prompt1, PersonData.class);
        List<PersonDataExtended> results2 = openAiClient.extractData(text, prompt2, PersonDataExtended.class);
        excelWriterService.saveToExistingExcel(results1, excelPath);
        excelWriterService.saveToExtendedExcel(results2, excelPath);
        log.info("Succesfully saved data to excel");

        return results1;
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
