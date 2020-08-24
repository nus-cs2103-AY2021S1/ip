@ECHO OFF

SET MY_PATH = C:\Users\User\Desktop\Y2S1\CS2103T\Individual Project
SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

REM create bin directory if it doesn't exist
if not exist "%MY_PATH%\bin" mkdir "%MY_PATH%\bin"

REM delete output from previous run
del ACTUAL.txt

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin duke.Duke < input.txt > ACTUAL.txt

REM compare the output to the expected output
FC ACTUAL.txt EXPECTED.txt
