@echo off
echo ⏳ Starting SwiftFood Build & Run Script

:: Set directories
set SRC_DIR=src
set BIN_DIR=bin
set LIB_DIR=lib

:: Create bin folder if not exists
if not exist %BIN_DIR% (
    mkdir %BIN_DIR%
)

echo 📦 Compiling...
javac -d %BIN_DIR% -cp "%LIB_DIR%/*" %SRC_DIR%\com\infy\**\*.java

if %errorlevel% neq 0 (
    echo ❌ Compilation Failed!
    pause
    exit /b
)

echo ✅ Compilation Successful
echo 🚀 Launching App...

:: Run the application
java -cp "%BIN_DIR%;%LIB_DIR%/*" com.infy.App

pause
