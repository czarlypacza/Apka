package com.example.serwis_pdf.service;

import com.example.serwis_pdf.model.PersonData;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TextExtractor {
    public String extractText(File file) throws Exception {
        try (PDDocument doc = PDDocument.load(file)) {
            return new PDFTextStripper().getText(doc);
        }
    }

    public List<PersonData> extractPersonData(String text) {
        List<PersonData> results = new ArrayList<>();

        Pattern peselPattern = Pattern.compile("\\bP[ĘE]?[S5][EĘ]?[LŁ1I][^\\d\\n]{0,10}?(\\d{11})\\b", Pattern.CASE_INSENSITIVE);
        Matcher peselMatcher = peselPattern.matcher(text);
        String pesel = peselMatcher.find() ? peselMatcher.group(1) : "";

        Pattern[] namePatterns = new Pattern[]{
                Pattern.compile("\\bimi[eę]\\s*i\\s*nazwisko\\s*:?\\s*=*\\s*([\\p{L}]+)\\s+([\\p{L}]+)", Pattern.CASE_INSENSITIVE),
                Pattern.compile("\\bnazwisko\\s*i\\s*imi[eę]\\s*:?\\s*=*\\s*([\\p{L}]+)\\s+([\\p{L}]+)", Pattern.CASE_INSENSITIVE),
                Pattern.compile("\\bimi[eę]\\s*:?\\s*([\\p{L}]+)\\s+nazwisko\\s*:?\\s*([\\p{L}]+)", Pattern.CASE_INSENSITIVE),
                Pattern.compile("\\bnazwisko\\s*:?\\s*([\\p{L}]+)\\s+imi[eę]\\s*:?\\s*([\\p{L}]+)", Pattern.CASE_INSENSITIVE)
        };

        String imie = "";
        String nazwisko = "";
        for (Pattern namePattern : namePatterns) {
            Matcher matcher = namePattern.matcher(text);
            if (matcher.find()) {
                imie = matcher.group(1);
                nazwisko = matcher.group(2);
                break;
            }
        }

        if (!pesel.isEmpty() || (!imie.isEmpty() && !nazwisko.isEmpty())) {
            results.add(new PersonData(nazwisko, imie, pesel));
        }

        return results;
    }
}
