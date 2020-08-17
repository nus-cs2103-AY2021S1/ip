@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL_TODO.TXT
del ACTUAL_DEADLINE.TXT
del ACTUAL_EVENT.TXT
del ACTUAL_MIXED.TXT
del ACTUAL_EXCEPTION.TXT
del ACTUAL_DELETE.TXT

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -encoding UTF-8 -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin Duke < input_todo.txt > ACTUAL_TODO.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin Duke < input_deadline.txt > ACTUAL_DEADLINE.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin Duke < input_event.txt > ACTUAL_EVENT.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin Duke < input_mixed.txt > ACTUAL_MIXED.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin Duke < input_exception.txt > ACTUAL_EXCEPTION.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin Duke < input_delete.txt > ACTUAL_DELETE.TXT

REM compare the output to the expected output
FC ACTUAL_TODO.TXT EXPECTED_TODO.TXT
pause
FC ACTUAL_DEADLINE.TXT EXPECTED_DEADLINE.TXT
pause
FC ACTUAL_EVENT.TXT EXPECTED_EVENT.TXT
pause
FC ACTUAL_MIXED.TXT EXPECTED_MIXED.TXT
pause
FC ACTUAL_EXCEPTION.TXT EXPECTED_EXCEPTION.TXT
pause
FC ACTUAL_DELETE.TXT EXPECTED_DELETE.TXT
pause
