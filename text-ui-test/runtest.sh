#!/usr/bin/env bash

# recreate bin directory
rm -rf ../bin
mkdir ../bin

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if
  ! (
    find ../src/main/java -name "*.java" > sources.txt
    javac -cp ../src -Xlint:none -d ../bin @sources.txt
  )
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# delete sources.txt
rm sources.txt

# copy properties files into the bin folder
find ../src/main -name "*.properties" -exec cp "{}" ../bin \;

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Duke < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
