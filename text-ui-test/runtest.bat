@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete any exisiting save file
if exist data\duke.txt del data\duke.txt
if exist data rmdir data

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
dir /s /B ..\src\main\java\*.java > sources.txt
javac  -cp ..\src -Xlint:none -d ..\bin @sources.txt
del sources.txt
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin duke.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

del data\duke.txt
rmdir data
