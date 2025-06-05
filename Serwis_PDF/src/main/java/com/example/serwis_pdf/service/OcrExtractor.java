package com.example.serwis_pdf.service;

import net.sourceforge.tess4j.Tesseract;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

@Service
public class OcrExtractor {
    @Value("${ocr.tessdata-path}")
    private String tessdataPath;

    public String extractTextFromScan(File file) throws Exception {
        try (PDDocument document = PDDocument.load(file)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            System.setProperty("TESSDATA_PREFIX", tessdataPath);

            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath(tessdataPath);
            tesseract.setLanguage("pol");

            StringBuilder sb = new StringBuilder();
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300);
                sb.append(tesseract.doOCR(image)).append("\n");
            }
            return sb.toString();
        }
    }
}
