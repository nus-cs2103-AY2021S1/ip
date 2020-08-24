@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\ -Xlint:none -encoding UTF-8 -d ..\bin ..\src\main\java\duke\exception\*.java ..\src\main\java\duke\*.java ..\src\main\java\duke\task\*.java
pause
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin duke.Duke < input.txt > ACTUAL.TXT
pause

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

