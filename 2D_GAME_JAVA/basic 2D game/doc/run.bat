@echo off
color a
echo cleaning old builds...
if exist "..\bin" rmdir /s /q "..\bin"
mkdir "..\bin"

echo Loading game...
dir /b /s "..\src\*.java" > sources.txt
javac  -d "..\bin" @sources.txt
del sources.txt
echo Starting Game : Treasure-Hunters!
pause
cls
java  -cp "..\bin" Main

pause