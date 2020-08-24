#!/usr/bin/env bash
set -e

# first compile the actual source
javac -sourcepath src/main/java -target 11 -source 11 -Xlint:unchecked -Xdiags:verbose $(find src/main/java -iname "*.java") -d bin/

# then the tests
javac -cp bin:lib/junit-jupiter-api-5.6.2.jar:lib/apiguardian-api-1.1.0.jar:lib/junit-jupiter-engine-5.6.2.jar -sourcepath src/test/java -target 11 -source 11 $(find src/test/java -iname "*.java") -d test-bin/

java -jar lib/junit-platform-console-standalone-1.7.0-RC1.jar --scan-classpath --class-path bin --class-path test-bin



if [ $? -ne 0 ]; then
	echo "tests failed"
	exit -1
else
	echo "tests passed"
	exit 0
fi
