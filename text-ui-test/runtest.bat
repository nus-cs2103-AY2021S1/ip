@ECHO OFF

SET JAVA=..\src\main\java
set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac -cp "%JAVA%" -Xlint:none -d ..\bin "%JAVA%\blue\Duke.java"
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********

)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin blue/Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
