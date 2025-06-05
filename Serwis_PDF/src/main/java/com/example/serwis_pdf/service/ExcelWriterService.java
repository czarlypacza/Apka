package com.example.serwis_pdf.service;

import com.example.serwis_pdf.model.PersonData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class ExcelWriterService {
    public void saveToExistingExcel(List<PersonData> people, String excelPath) throws Exception {
        File excelFile = new File(excelPath);
        try (InputStream is = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(is)) {

            for (int i = 0; i < 2; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                int rowNum = sheet.getLastRowNum() + 1;

                for (PersonData person : people) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(person.getLastName());
                    row.createCell(1).setCellValue(person.getFirstName());
                    row.createCell(2).setCellValue(person.getPesel());
                }
            }

            try (FileOutputStream fos = new FileOutputStream(excelFile)) {
                workbook.write(fos);
            }
        }
    }
}
