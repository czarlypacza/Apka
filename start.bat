@echo off

echo Starting backend...
start powershell -NoProfile -ExecutionPolicy Bypass -Command "cd Serwis_PDF; .\mvnw spring-boot:run"

echo Starting frontend...
start powershell -NoProfile -ExecutionPolicy Bypass -Command "cd front; npm install; npm run dev"

echo Opening browser at http://localhost:5173...
start http://localhost:5173

echo Both windows should open now. Press any key in this window to close.
pause