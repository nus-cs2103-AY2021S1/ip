@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ../src/main/java/viscount/*.java ../src/main/java/viscount/command/*.java ../src/main/java/viscount/exception/*.java ../src/main/java/viscount/task/*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from the input files and redirect the output to the ACTUAL.TXT
java -classpath ..\bin viscount.Viscount < input1.txt >> ACTUAL.TXT
java -classpath ..\bin viscount.Viscount < input2.txt >> ACTUAL.TXT
java -classpath ..\bin viscount.Viscount < input3.txt >> ACTUAL.TXT

REM delete data afterwards
del data\viscount.txt

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
