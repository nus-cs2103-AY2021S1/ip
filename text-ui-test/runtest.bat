@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM move resources into the bin folder
rmdir ..\bin\resources\ /s /q
xcopy /s ..\src\main\java\resources ..\bin\resources\

set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

REM compile the code into the bin folder
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\tasks\*.java
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\commands\*.java
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\exceptions\*.java
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\Duke.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
