#!/usr/bin/env bash
set -e

javac -sourcepath src/main/java -target 11 -source 11 -Xlint:unchecked -Xdiags:verbose src/main/java/**.java -d bin/
java -cp bin Main
