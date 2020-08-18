@ECHO OFF

REM discard any changes to env vars when script exits
setlocal enabledelayedexpansion

REM recreate bin directory
rmdir /s /q ..\bin
mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
(for /f "delims=" %%f in ('dir /b /s ..\src\main\*.java') do (
    set file=%%f
    set file=!file:\=\\!
    @echo "!file!"
)) > sources.txt
javac -cp ..\src -Xlint:none -d ..\bin @sources.txt
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM copy properties files into the bin folder
(for /f "delims=" %%f in ('dir /b /s ..\src\main\*.properties') do (
    set file=%%f
    set file=!file:\=\\!
    @echo "!file!"
)) > sources.txt
for /f "delims=" %%f in (sources.txt) do copy %%f ..\bin

REM delete sources.txt
del sources.txt

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -D"file.encoding=UTF-8" -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
