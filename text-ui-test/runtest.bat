@ECHO OFF

set PATH=%PATH%;C:\Program Files\Java\jdk-11.0.8\bin
set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

SET MY_PATH=C:\Users\Lee\Dropbox\Y2S1\CS2103T\Duke

REM create bin directory if it doesn't exist
if not exist "%MY_PATH%\bin" mkdir "%MY_PATH%\bin"


REM compile the code into the bin folder
javac  -cp "%MY_PATH%\src\main\java" -Xlint:none -d "%MY_PATH%\bin" "%MY_PATH%\src\main\java\*.java"
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath "%MY_PATH%\bin" main.java.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

PAUSE