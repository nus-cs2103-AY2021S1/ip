@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM compile the code into the bin folder
javac  -cp "C:\Users\Jia Le\Desktop\CS2103W2\src" -Xlint:none -d ..\bin "C:\Users\Jia Le\Desktop\CS2103W2\src\main\java\Duke.java" "C:\Users\Jia Le\Desktop\CS2103W2\src\main\java\Task.java" "C:\Users\Jia Le\Desktop\CS2103W2\src\main\java\ToDo.java" "C:\Users\Jia Le\Desktop\CS2103W2\src\main\java\Event.java" "C:\Users\Jia Le\Desktop\CS2103W2\src\main\java\Deadline.java"
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
"C:\Program Files\Java\jdk-13.0.1\bin\java" -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
