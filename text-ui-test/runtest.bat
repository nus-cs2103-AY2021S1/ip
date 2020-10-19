@ECHO OFF

SET MY_PATH=D:\CY stuff\NUS\5. AY2020 Sem 1 Actual\CS2103T\ip
REM create bin directory if it doesn't exist
if not exist "%MY_PATH%\bin" mkdir "%MY_PATH%\bin"

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp "%MY_PATH%\src\main\java" -Xlint:none -d "%MY_PATH%\bin" "%MY_PATH%\src\main\java\Duke.java"
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath "%MY_PATH%\bin" Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
cmd