@ECHO OFF



REM create bin directory if it doesn't exist
if not exist C:\Data\repos\CS2103\bin mkdir C:\Data\repos\CS2103\bin

REM delete output from previous run
del ACTUAL.TXT

set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

REM compile the code into the bin folder
javac  -cp C:\Data\repos\CS2103\src\main\java\ -Xlint:none -d C:\Data\repos\CS2103\bin C:\Data\repos\CS2103\src\main\java\Duke\*.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath C:\Data\repos\CS2103\bin Duke.Duke < input.txt > ACTUAL.TXT


REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

