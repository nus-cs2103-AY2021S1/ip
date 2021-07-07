@ECHO OFF

if not exist ..\bin mkdir ..\bin

del ACTUAL.TXT

javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
    
)

java -classpath ..\bin Duke < input.txt > ACTUAL.TXT


FC ACTUAL.TXT EXPECTED.TXT
