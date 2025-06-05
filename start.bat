@echo off

echo Starting backend...
start powershell -NoProfile -ExecutionPolicy Bypass -Command "cd Serwis_PDF; .\mvnw spring-boot:run"

echo Starting frontend...
start powershell -NoProfile -ExecutionPolicy Bypass -Command "cd front; npm install; npm run dev"

echo Both windows should open now. Press any key in this window to close.
pause