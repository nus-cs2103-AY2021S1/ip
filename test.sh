#!/usr/bin/env bash
set -e

javac -sourcepath src/main/java -target 11 -source 11 src/main/java/**.java -d bin/
java -cp bin Main < tests/input.txt | diff -bu tests/EXPECTED.TXT -

if [ $? -ne 0 ]; then
	echo "test failed"
	exit -1
else
	echo "test passed"
	exit 0
fi
