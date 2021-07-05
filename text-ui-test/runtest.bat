@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp C:\repos\ip\src -Xlint:none -d C:\repos\ip\bin C:\repos\ip\src\main\java\DukeException.java C:\repos\ip\src\main\java\TrackingException.java C:\repos\ip\src\main\java\CommandException.java C:\repos\ip\src\main\java\DescriptionException.java C:\repos\ip\src\main\java\Task.java C:\repos\ip\src\main\java\Deadline.java C:\repos\ip\src\main\java\Events.java C:\repos\ip\src\main\java\ToDo.java C:\repos\ip\src\main\java\Duke.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath C:\repos\ip\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

PAUSE