#!/usr/bin/env bash
set -e

javac -target 11 -source 11 src/main/java/**.java -d bin/
java -cp bin Main < text-ui-test/input.txt | diff -bu text-ui-test/EXPECTED.TXT -

if [ $? -ne 0 ]; then
	echo "test failed"
	exit -1
else
	echo "test passed"
	exit 0
fi
