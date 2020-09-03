#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

mv $HOME/nekochan/localData/data.neko $HOME/nekochan/localData/data.neko.temp

# compile the code into the bin folder, terminates if error occurred
# exclude compilation of GUI related files
if ! (
  find ../src/main/java/nekochan -name "*.java" > sources.txt
  sed '/.*DialogBox.*/d' sources.txt > sources1.txt
  sed '/.*MainWindow.*/d' sources1.txt > sources2.txt
  javac -cp ../src -Xlint:none -d ../bin @sources2.txt
)
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin nekochan.CliWrapper < input.txt > ACTUAL.TXT

# cleanup temporary files
find . -name "sources*.txt" -delete

mv $HOME/nekochan/localData/data.neko.temp $HOME/nekochan/localData/data.neko

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