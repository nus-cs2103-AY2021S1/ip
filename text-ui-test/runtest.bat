@ECHO OFF

REM create bin directory if it doesn't exist
rem if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
rem javac -cp .\lib\*.jar -Xlint:none -d ..\bin ..\src\main\java\duke\Main.java ..\src\main\java\duke\cmd\*.java ..\src\main\java\duke\command\*.java ..\src\main\java\duke\task\*.java ..\src\main\java\duke\storage\*.java ..\src\main\java\duke\util\*.java ..\src\main\java\duke\parser\*.java ..\src\main\java\duke\gui\*.java
rem IF ERRORLEVEL 1 (
rem     echo ********** BUILD FAILURE **********
rem     exit /b 1
rem )
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -jar ..\build\libs\duke.jar cmd < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
