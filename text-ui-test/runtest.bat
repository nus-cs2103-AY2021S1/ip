@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\taskbot\ -Xlint:none -d ..\bin ..\src\taskbot\exceptions\*.java ..\src\taskbot\task\*.java ..\src\taskbot\logic\*.java ..\src\taskbot\main\Main.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
)
REM no error here, error level == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin taskbot/main/Main < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

cmd /k