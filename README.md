# Wymagania i uruchomienie

## 1. Wymagane komponenty
- Java JDK 21
- Node.js v22.16.0

## 2. Instalacja
- Java JDK, do pobrania ze strony: https://www.oracle.com/java/technologies/downloads/#jdk21-windows lub link https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.exe
- Node.js, do pobrania ze strony: https://nodejs.org/en lub link https://nodejs.org/dist/v22.16.0/node-v22.16.0-x64.msi

## 3. Uruchomienie frontendu (React)
1. Otwórz konsolę w folderze `.\front` (klik prawym przyciskiem myszy na folder i "otworz w terminalu")  
2. Zainstaluj zależności:  
   ```
   npm install
   ```
3. Uruchom tryb deweloperski:  
   ```
   npm run dev
   ```
4. Serwer deweloperski uruchomi sie domyślnie na porcie 5173.

## 4. Uruchomienie backendu (Spring)
1. Otwórz konsolę w folderze `.\Serwis_PDF` (klik prawym przyciskiem myszy na folder i "otworz w terminalu")    
2. Wpisz:  
   ```
   .\mvnw spring-boot:run
   ```
3. Serwis uruchomi się domyślnie na porcie 8080.

## 5. Otworzenie aplikacji w przeglądarce internetowej

1. W przeglądarce przejdź do:  
   ```
   http://localhost:5173
   ```