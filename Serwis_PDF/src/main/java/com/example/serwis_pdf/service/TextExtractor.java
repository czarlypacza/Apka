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

        List<NamePatternInfo> namePatterns = List.of(
                new NamePatternInfo(Pattern.compile("\\bimi[eę]\\s*i\\s*nazwisko\\s*:?\\s*=*\\s*([\\p{L}]+)\\s+([\\p{L}]+)", Pattern.CASE_INSENSITIVE), 1, 2),
                new NamePatternInfo(Pattern.compile("\\bnazwisko\\s*i\\s*imi[eę]\\s*:?\\s*=*\\s*([\\p{L}]+)\\s+([\\p{L}]+)", Pattern.CASE_INSENSITIVE), 2, 1),
                new NamePatternInfo(Pattern.compile("\\bimi[eę]\\s*:?\\s*([\\p{L}]+)\\s+nazwisko\\s*:?\\s*([\\p{L}]+)", Pattern.CASE_INSENSITIVE), 1, 2),
                new NamePatternInfo(Pattern.compile("\\bnazwisko\\s*:?\\s*([\\p{L}]+)\\s+imi[eę]\\s*:?\\s*([\\p{L}]+)", Pattern.CASE_INSENSITIVE), 2, 1)
        );

        String imie = "";
        String nazwisko = "";
        for (NamePatternInfo info : namePatterns) {
            Matcher matcher = info.pattern.matcher(text);
            if (matcher.find()) {
                imie = matcher.group(info.imieGroup);
                nazwisko = matcher.group(info.nazwiskoGroup);
                break;
            }
        }

        if (!pesel.isEmpty() || (!imie.isEmpty() && !nazwisko.isEmpty())) {
            results.add(new PersonData(imie, nazwisko, pesel));
        }

        return results;
    }
}

class NamePatternInfo {
    Pattern pattern;
    int imieGroup;
    int nazwiskoGroup;

    NamePatternInfo(Pattern pattern, int imieGroup, int nazwiskoGroup) {
        this.pattern = pattern;
        this.imieGroup = imieGroup;
        this.nazwiskoGroup = nazwiskoGroup;
    }
}