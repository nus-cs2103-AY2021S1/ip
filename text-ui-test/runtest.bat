@ECHO OFF

REM create bin directory if it doesn't exist
if not exist D:\CS2103T\ip\bin mkdir D:\CS2103T\ip\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8
javac  -cp D:\CS2103T\ip\src\ -Xlint:none -d D:\CS2103T\ip\bin D:\CS2103T\ip\src\main\java\*.java -target 11
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath D:\CS2103T\ip\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

pause