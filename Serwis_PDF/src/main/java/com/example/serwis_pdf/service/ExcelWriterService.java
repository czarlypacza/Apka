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


            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum() + 1;

            for (PersonData person : people) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(person.getLastName());
                row.createCell(1).setCellValue(person.getFirstName());
                row.createCell(2).setCellValue(person.getPesel());
                row.createCell(3).setCellValue(person.getNumer_telefonu());
                row.createCell(4).setCellValue(person.getRyzykoZgonu());
                row.createCell(5).setCellValue(person.getDataPrzyjecia());
                row.createCell(6).setCellValue(person.getSzpital());
                row.createCell(7).setCellValue(person.getOddzial());
                row.createCell(8).setCellValue(person.getLekarzProwadzacy());
                row.createCell(9).setCellValue(person.getMiejsceHospitalizacji());
                row.createCell(10).setCellValue(person.getDataWypisu());
                row.createCell(11).setCellValue(person.getCzasHospitalizacji());
                row.createCell(12).setCellValue(person.getPlec());
                row.createCell(13).setCellValue(person.getWiek());
                row.createCell(14).setCellValue(person.getMasaCiala());
                row.createCell(15).setCellValue(person.getWzrost());
                row.createCell(16).setCellValue(person.getBmi());
            }


            try (FileOutputStream fos = new FileOutputStream(excelFile)) {
                workbook.write(fos);
            }
        }
    }

    public void saveToExtendedExcel(List<com.example.serwis_pdf.model.PersonDataExtended> people, String excelPath) throws Exception {
        File excelFile = new File(excelPath);
        try (InputStream is = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(1); // Drugi arkusz
            int rowNum = sheet.getLastRowNum() + 1;

            for (com.example.serwis_pdf.model.PersonDataExtended person : people) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(person.getLastName());
                row.createCell(1).setCellValue(person.getFirstName());
                row.createCell(2).setCellValue(person.getPesel());
                row.createCell(3).setCellValue(person.getCzasTrwaniaObjawow());
                row.createCell(4).setCellValue(person.getNzk());
                row.createCell(5).setCellValue(person.getWstrzas());
                row.createCell(6).setCellValue(person.getOmdlenie());
                row.createCell(7).setCellValue(person.getDusznosc());
                row.createCell(8).setCellValue(person.getBolWKlatce());
                row.createCell(9).setCellValue(person.getZmniejszenieTolerancji());
                row.createCell(10).setCellValue(person.getKaszel());
                row.createCell(11).setCellValue(person.getKrwioplucie());
                row.createCell(12).setCellValue(person.getZaburzeniaSwiadomosci());
                row.createCell(13).setCellValue(person.getInneObjawy());
                row.createCell(14).setCellValue(person.getChorobyWspolistniejace());
                row.createCell(15).setCellValue(person.getZakrzepicaAktualna());
                row.createCell(16).setCellValue(person.getLokalizacjaZakrzepu());
                row.createCell(17).setCellValue(person.getZakrzepicaProksymalna());
                row.createCell(18).setCellValue(person.getZatorowoscPlucnaWPrzeszlosci());
                row.createCell(19).setCellValue(person.getZakrzepicaWPrzeszlosci());
                row.createCell(20).setCellValue(person.getAlloplastyka());
                row.createCell(21).setCellValue(person.getZlamanie());
                row.createCell(22).setCellValue(person.getPowaznyUraz6mies());
                row.createCell(23).setCellValue(person.getUrazRdzenia());
                row.createCell(24).setCellValue(person.getHospitalizacjaMigotanie12mies());
                row.createCell(25).setCellValue(person.getZawal3mies());
                row.createCell(26).setCellValue(person.getArtroskopia6mies());
                row.createCell(27).setCellValue(person.getAntykoncepcja());
                row.createCell(28).setCellValue(person.getHormonalnaTerapia());
                row.createCell(29).setCellValue(person.getInVitro12mies());
                row.createCell(30).setCellValue(person.getPoloz());
                row.createCell(31).setCellValue(person.getPrzetoczenieKKCzMiesiac());
                row.createCell(32).setCellValue(person.getErytropoezaMiesiac());
                row.createCell(33).setCellValue(person.getCewnikCentralny());
                row.createCell(34).setCellValue(person.getElektrodyZylne());
                row.createCell(35).setCellValue(person.getZakrzepicaPowierzchowna());
                row.createCell(36).setCellValue(person.getTrombofilia());
                row.createCell(37).setCellValue(person.getNiewydolnoscSerceTyp());
                row.createCell(38).setCellValue(person.getPrzewleklaChorobaOddechowa());
                row.createCell(39).setCellValue(person.getInfekcjaTyp());
                row.createCell(40).setCellValue(person.getChZapJelit());
                row.createCell(41).setCellValue(person.getNowotworZPrzerzutami());
                row.createCell(42).setCellValue(person.getChemioterapia());
                row.createCell(43).setCellValue(person.getChorobyAutoimmunizacyjne());
                row.createCell(44).setCellValue(person.getUdarMozguNiedowlady());
                row.createCell(45).setCellValue(person.getUnieruchomienieLozko3dni());
                row.createCell(46).setCellValue(person.getUnieruchomieniePodroz4h());
                row.createCell(47).setCellValue(person.getWiek80plus());
                row.createCell(48).setCellValue(person.getOtylosc());
                row.createCell(49).setCellValue(person.getNadcisnienieTetnicze());
                row.createCell(50).setCellValue(person.getCukrzyca());
                row.createCell(51).setCellValue(person.getNiewydolnoscZylna());
                row.createCell(52).setCellValue(person.getCiaza());
                row.createCell(53).setCellValue(person.getChirurgiaLaparoskopowaMiesiac());
                row.createCell(54).setCellValue(person.getNiedoczynnoscTarczycy());
                row.createCell(55).setCellValue(person.getNadczynnoscTarczycy());
                row.createCell(56).setCellValue(person.getZawalWywiad());
                row.createCell(57).setCellValue(person.getMigotaniePrzedsionkow());
                row.createCell(58).setCellValue(person.getNowotwor());
                row.createCell(59).setCellValue(person.getNowotworOpis());
                row.createCell(60).setCellValue(person.getNiewydolnoscSerca());
                row.createCell(61).setCellValue(person.getPrzewleklaChorobaPluc());
                row.createCell(62).setCellValue(person.getHr());
                row.createCell(63).setCellValue(person.getHr110plus());
                row.createCell(64).setCellValue(person.getSbp());
                row.createCell(65).setCellValue(person.getSbp100minus());
                row.createCell(66).setCellValue(person.getDbp());
                row.createCell(67).setCellValue(person.getAminyPresyjneTyp());
                row.createCell(68).setCellValue(person.getAminyOpis());
                row.createCell(69).setCellValue(person.getSpo2());
                row.createCell(70).setCellValue(person.getSatO290minus());
                row.createCell(71).setCellValue(person.getTlenoterapiaTyp());
                row.createCell(72).setCellValue(person.getCzestoscOddechow());
                row.createCell(73).setCellValue(person.getCzestoscOddechow30plus());
                row.createCell(74).setCellValue(person.getTemperaturaC());
                row.createCell(75).setCellValue(person.getSplatanie());
                row.createCell(76).setCellValue(person.getUdarKrwotoczny());
                row.createCell(77).setCellValue(person.getUdarNiedokrwienny6mies());
                row.createCell(78).setCellValue(person.getUszkodzenieOUN());
                row.createCell(79).setCellValue(person.getPowaznaOperacjaUraz14dni());
                row.createCell(80).setCellValue(person.getAktywneKrwawienie());
                row.createCell(81).setCellValue(person.getRyzykoKrwawienia());
                row.createCell(82).setCellValue(person.getTia6mies());
                row.createCell(83).setCellValue(person.getLeczeniePrzeciwkrzepliwe());
                row.createCell(84).setCellValue(person.getCiazaPorodTydzien());
                row.createCell(85).setCellValue(person.getMiejsceWkluciaNieUcisk());
                row.createCell(86).setCellValue(person.getUrazResuscytacja());
                row.createCell(87).setCellValue(person.getOporneNadcisnienie());
                row.createCell(88).setCellValue(person.getZaawansowanaChorobaWatroby());
                row.createCell(89).setCellValue(person.getBakteryjneZapalenieWsierdzia());
                row.createCell(90).setCellValue(person.getAktywnyWrzodTrawienny());
                row.createCell(91).setCellValue(person.getTypAngioCT1());
                row.createCell(92).setCellValue(person.getTypAngioCTPrawa());
                row.createCell(93).setCellValue(person.getTypAngioCTLewa());
                row.createCell(94).setCellValue(person.getZawalPlucaZatorowosc());
                row.createCell(95).setCellValue(person.getSkrzeplinyPrawyPrzedsionek());
                row.createCell(96).setCellValue(person.getStosunekKomoryCT());
                row.createCell(97).setCellValue(person.getBaselineData());
                row.createCell(98).setCellValue(person.getBaselineGodzina());
                row.createCell(99).setCellValue(person.getTroponinaHsT());
                row.createCell(100).setCellValue(person.getNtproBNP());
                row.createCell(101).setCellValue(person.getDdimer());
                row.createCell(102).setCellValue(person.getInr());
                row.createCell(103).setCellValue(person.getAptt());
                row.createCell(104).setCellValue(person.getFibrynogen());
                row.createCell(105).setCellValue(person.getRbc());
                row.createCell(106).setCellValue(person.getHgb());
                row.createCell(107).setCellValue(person.getHct());
                row.createCell(108).setCellValue(person.getPlt());
                row.createCell(109).setCellValue(person.getCrp());
                row.createCell(110).setCellValue(person.getAlat());
                row.createCell(111).setCellValue(person.getAspat());
                row.createCell(112).setCellValue(person.getCrea());
                row.createCell(113).setCellValue(person.getGfr());
                row.createCell(114).setCellValue(person.getLac());
                row.createCell(115).setCellValue(person.getGrupaKrwi());
                row.createCell(116).setCellValue(person.getDrugiDzienData());
                row.createCell(117).setCellValue(person.getDrugiDzienGodzina());
                row.createCell(118).setCellValue(person.getDrugiDzienTroponina());
                row.createCell(119).setCellValue(person.getDrugiDzienNtproBNP());
                row.createCell(120).setCellValue(person.getDrugiDzienDdimer());
                row.createCell(121).setCellValue(person.getDrugiDzienFibrynogen());
                row.createCell(122).setCellValue(person.getDrugiDzienPlazminogen());
                row.createCell(123).setCellValue(person.getDrugiDzienAlfa2Antyplazmina());
                row.createCell(124).setCellValue(person.getDrugiDzienInr());
                row.createCell(125).setCellValue(person.getDrugiDzienAptt());
                row.createCell(126).setCellValue(person.getDrugiDzienRbc());
                row.createCell(127).setCellValue(person.getDrugiDzienHgb());
                row.createCell(128).setCellValue(person.getDrugiDzienHct());
                row.createCell(129).setCellValue(person.getDrugiDzienPlt());
                row.createCell(130).setCellValue(person.getDrugiDzienCrp());
                row.createCell(131).setCellValue(person.getDrugiDzienAlat());
                row.createCell(132).setCellValue(person.getDrugiDzienAspat());
                row.createCell(133).setCellValue(person.getDrugiDzienCrea());
                row.createCell(134).setCellValue(person.getDrugiDzienGfr());
                row.createCell(135).setCellValue(person.getDrugiDzienLac());
                row.createCell(136).setCellValue(person.getOstatniDzienData());
                row.createCell(137).setCellValue(person.getOstatniDzienGodzina());
                row.createCell(138).setCellValue(person.getOstatniDzienTroponina());
                row.createCell(139).setCellValue(person.getOstatniDzienNtproBNP());
                row.createCell(140).setCellValue(person.getOstatniDzienDdimer());
                row.createCell(141).setCellValue(person.getOstatniDzienFibrynogen());
                row.createCell(142).setCellValue(person.getOstatniDzienPlazminogen());
                row.createCell(143).setCellValue(person.getOstatniDzienAlfa2Antyplazmina());
                row.createCell(144).setCellValue(person.getOstatniDzienInr());
                row.createCell(145).setCellValue(person.getOstatniDzienAptt());
                row.createCell(146).setCellValue(person.getOstatniDzienRbc());
                row.createCell(147).setCellValue(person.getOstatniDzienHgb());
                row.createCell(148).setCellValue(person.getOstatniDzienHct());
                row.createCell(149).setCellValue(person.getOstatniDzienPlt());
                row.createCell(150).setCellValue(person.getOstatniDzienCrp());
                row.createCell(151).setCellValue(person.getOstatniDzienAlat());
                row.createCell(152).setCellValue(person.getOstatniDzienAspat());
                row.createCell(153).setCellValue(person.getOstatniDzienCrea());
                row.createCell(154).setCellValue(person.getOstatniDzienGfr());
                row.createCell(155).setCellValue(person.getOstatniDzienLac());
            }

            try (FileOutputStream fos = new FileOutputStream(excelFile)) {
                workbook.write(fos);
            }
        }
    }
}
