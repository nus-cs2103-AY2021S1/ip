@ECHO OFF

REM delete display from previous run
del ACTUAL.TXT

REM run the program, feed commands from input.txt file and redirect the display to the ACTUAL.TXT
java -jar ..\build\libs\duke.jar cmd < input.txt > ACTUAL.TXT 2>&1

REM compare the display to the expected display
FC ACTUAL.TXT EXPECTED.TXT
