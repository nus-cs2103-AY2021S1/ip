@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT
del D:\uni\CS2103T\Duke(iP)\ip\text-ui-test\data\duke.txt

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin D:\uni\CS2103T\Duke(iP)\ip\src\main\java\Duke.java D:\uni\CS2103T\Duke(iP)\ip\src\main\java\Task\* D:\uni\CS2103T\Duke(iP)\ip\src\main\java\DukeExceptions.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath D:\uni\CS2103T\Duke(iP)\ip\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT