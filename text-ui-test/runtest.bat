@ECHO OFF

SET MY_PATH=C:\Users\cs623\Docs\GitHub\ip

REM create bin directory if it doesn't exist
if not exist "%MY_PATH%\bin" mkdir "%MY_PATH%\bin"

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp "%MY_PATH%\src\main\java\duke.duke" -Xlint:none -d "%MY_PATH%\bin" "%MY_PATH%\src\main\java\duke.duke\duke.duke.java"
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin duke.duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC /w ACTUAL.TXT EXPECTED.TXT
