#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL_TODO.TXT" ]
then
    rm ACTUAL_TODO.TXT
fi

if [ -e "./ACTUAL_DEADLINE.TXT" ]
then
    rm ACTUAL_DEADLINE.TXT
fi

if [ -e "./ACTUAL_EVENT.TXT" ]
then
    rm ACTUAL_EVENT.TXT
fi

if [ -e "./ACTUAL_MIXED.TXT" ]
then
    rm ACTUAL_MIXED.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src -Xlint:none -encoding UTF-8 -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -Dfile.encoding=UTF-8 -classpath ../bin Duke < input_todo.txt > ACTUAL_TODO.TXT
java -classpath ../bin Duke < input_deadline.txt > ACTUAL_DEADLINE.TXT
java -classpath ../bin Duke < input_event.txt > ACTUAL_EVENT.TXT
java -classpath ../bin Duke < input_mixed.txt > ACTUAL_MIXED.TXT

# convert to UNIX format
cp EXPECTED_TODO.TXT EXPECTED_TODO-UNIX.TXT
cp EXPECTED_DEADLINE.TXT EXPECTED_DEADLINE-UNIX.TXT
cp EXPECTED_EVENT.TXT EXPECTED_EVENT-UNIX.TXT
cp EXPECTED_MIXED.TXT EXPECTED_MIXED-UNIX.TXT
dos2unix ACTUAL_TODO.TXT EXPECTED_TODO-UNIX.TXT
dos2unix ACTUAL_DEADLINE.TXT EXPECTED_DEADLINE-UNIX.TXT
dos2unix ACTUAL_EVENT.TXT EXPECTED_EVENT-UNIX.TXT
dos2unix ACTUAL_MIXED.TXT EXPECTED_MIXED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL_TODO.TXT EXPECTED_TODO-UNIX.TXT
diff ACTUAL_DEADLINE.TXT EXPECTED_DEADLINE-UNIX.TXT
diff ACTUAL_EVENT.TXT EXPECTED_EVENT-UNIX.TXT
diff ACTUAL_MIXED.TXT EXPECTED_MIXED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi