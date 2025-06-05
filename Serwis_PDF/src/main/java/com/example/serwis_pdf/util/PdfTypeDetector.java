package com.example.serwis_pdf.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class PdfTypeDetector {
    public static boolean isTextBasedPdf(File file) {
        try (PDDocument doc = PDDocument.load(file)) {
            String text = new PDFTextStripper().getText(doc);
            return text != null && text.trim().length() > 100;
        } catch (Exception e) {
            return false;
        }
    }

}
