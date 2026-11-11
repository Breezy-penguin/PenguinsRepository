@echo off
REM Run this from the docs folder.
REM Folder layout expected (all siblings under one parent):
REM   parent/
REM     src/   -> .java source files
REM     bin/   -> compiled .class files go here
REM     docs/  -> this batch file

color a

setlocal

set SRC=..\src
set BIN=..\bin

echo Compiling Java sources from %SRC% into %BIN% ...
javac -encoding UTF-8 -d %BIN% %SRC%\*.java

if errorlevel 1 (
    echo.
    echo Compilation failed. Fix the errors above and try again.
    endlocal
    exit /b 1
)

echo.
echo Compilation succeeded.
echo Loading...
echo.

pause
cls
echo PENGUIN PROJECTS PRESENTS
pause 
cls
echo ALIEN SHOOTER 
pause 
cls
echo Welcome to ALLIEN SHOOTER by PENGUIN PROJECTS! You are now a space adventurer. Exciting right! 
echo Unfortunately for you, there are devious alien fiends who think you are very tasty. 
echo Whether you survive or become a five course meal is all up to your battle skill. Shoot the enemy 
echo and avoid being dinner. GOOD LUCK!
echo START GAME...
pause
cls

REM For handling emojis
chcp 65001 
java -cp %BIN% Main

endlocal