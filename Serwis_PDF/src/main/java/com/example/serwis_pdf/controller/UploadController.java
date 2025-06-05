package com.example.serwis_pdf.controller;

import com.example.serwis_pdf.model.PersonData;
import com.example.serwis_pdf.service.PdfProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/pdf")
public class UploadController {


    @Autowired
    private PdfProcessingService pdfProcessingService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> handlePdfUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("excelPath") String excelPath) {
        try {
            List<PersonData> people = pdfProcessingService.processPdf(file, excelPath);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("status", "success");
            responseBody.put("count", people.size());
            responseBody.put("message", "PDF processed successfully");
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("status", "error");
            errorBody.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorBody);
        }
    }


}
