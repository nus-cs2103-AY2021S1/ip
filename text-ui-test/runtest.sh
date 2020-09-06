#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]; then
  mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]; then
  rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
# TODO Write more portable shell script
if ! javac -cp ../src/main/java/dev/jingyen/duke -Xlint:none -d ../bin \
  ../src/main/java/dev/jingyen/duke/Command.java \
  ../src/main/java/dev/jingyen/duke/Duke.java \
  ../src/main/java/dev/jingyen/duke/TaskList.java \
  ../src/main/java/dev/jingyen/duke/Ui.java \
  ../src/main/java/dev/jingyen/duke/parser/*.java \
  ../src/main/java/dev/jingyen/duke/storage/*.java \
  ../src/main/java/dev/jingyen/duke/model/*.java; then
  echo "********** BUILD FAILURE **********"
  exit 1
fi

# Remove save file if exists
[ -e "$HOME/.duke/tasks.txt" ] && rm -f "$HOME/.duke/tasks.txt"

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin dev.jingyen.duke.Duke <input.txt >ACTUAL.TXT

# Remove save file if exists
[ -e "$HOME/.duke/tasks.txt" ] && rm -f "$HOME/.duke/tasks.txt"

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]; then
  echo "Test result: PASSED"
  exit 0
else
  echo "Test result: FAILED"
  exit 1
fi
