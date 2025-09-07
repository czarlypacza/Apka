package com.example.serwis_pdf.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class PersonData {
    private String firstName;
    private String lastName;
    private String pesel;
    private String numer_telefonu;
    private int ryzykoZgonu; // 0-HR, 1-IHR, 2-ILR, 3-LR
    private String dataPrzyjecia;
    private String szpital;
    private String oddzial;
    private String lekarzProwadzacy;
    private int miejsceHospitalizacji; // 0 - zwykłe łóżka, 1 - OIOM/INT
    private String dataWypisu;
    private int czasHospitalizacji;
    private int plec; // 0-M, 1-K
    private int wiek;
    private double masaCiala;
    private double wzrost;
    private double bmi;

}
