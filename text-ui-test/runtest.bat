@ECHO OFF

SET MY_PATH=C:\Users\Wong Jun Long\Documents\ip

REM create bin directory if it doesn't exist
if not exist "%MY_PATH%\bin" mkdir "%MY_PATH%\bin"

REM delete output from previous run
del ACTUAL.TXT

REM reset duke.txt for every run so that we will not carry forward the previous test state
break > duke.txt

REM compile the code into the bin folder
dir /s /B ..\src\main\java\*.java > sources.txt
javac -cp "%MY_PATH%\src\main\java" -Xlint:none -d "%MY_PATH%\bin" "%MY_PATH%\src\main\java\Duke.java" "%MY_PATH%\src\main\java\Task.java" "%MY_PATH%\src\main\java\Todo.java" "%MY_PATH%\src\main\java\Deadline.java" "%MY_PATH%\src\main\java\Event.java" "%MY_PATH%\src\main\java\TaskManager.java" "%MY_PATH%\src\main\java\DukeException.java" "%MY_PATH%\src\main\java\FileManipulator.java"
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath "%MY_PATH%\bin" Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
