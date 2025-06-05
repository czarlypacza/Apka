# Wymagania i uruchomienie

## 1. Wymagane komponenty
- Java JDK 21  
- Node.js v22.16.0

## 2. Instalacja
- [Java JDK 21](https://www.oracle.com/java/technologies/downloads/#jdk21-windows) (lub [bezpośredni link do pobrania](https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.exe))  
- [Node.js v22.16.0](https://nodejs.org/en) (lub [bezpośredni link do pobrania](https://nodejs.org/dist/v22.16.0/node-v22.16.0-x64.msi))

## 3. Uruchomienie aplikacji
1. **Skrypt `start.bat`**  
   - Kliknij dwukrotnie w plik  
   - Jeśli pojawi się ostrzeżenie, kliknij w <u>podkreślony tekst</u> i wybierz „Uruchom”  
2. **Instrukcje ręczne**  
   - Opisane w [3.1](#31-uruchomienie-frontendu-react) i [3.2](#32-uruchomienie-backendu-spring)

## 3.1 Uruchomienie frontendu (React)
1. Otwórz konsolę w folderze `.\front` (np. klik prawym przyciskiem → „Otwórz w terminalu”)  
2. Zainstaluj zależności:  
   ```
   npm install
   ```
3. Uruchom tryb deweloperski:  
   ```
   npm run dev
   ```

## 3.2 Uruchomienie backendu (Spring)
1. Otwórz konsolę w folderze `.\Serwis_PDF` (np. klik prawym przyciskiem → „Otwórz w terminalu”)   
2. Wpisz:  
   ```
   .\mvnw spring-boot:run
   ```

## 4. Otworzenie aplikacji w przeglądarce
Wpisz w przeglądarce w polu wyszukiwania na górze strony:
```
http://localhost:5173
```

## 5. Rozwiązywanie problemów z PowerShell
Jeżeli otrzymujesz błąd o blokowaniu skryptów:
- **Tymczasowe odblokowanie** w bieżącej sesji terminala (nie wprowadza trwałych zmian):
  ```
  Set-ExecutionPolicy RemoteSigned -Scope Process
  ```
- **Trwałe zezwolenie** (ostrożnie, wprowadza zmiany systemowe – wymaga uruchomienia terminala jako administrator):
  ```
  Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
  ```