// MainTest.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.io.ByteArrayOutputStream;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

import ikura.Bot;
import ikura.Database;
import ikura.TaskList;
import ikura.TextFrontend;

import ikura.util.Pair;
import ikura.util.Either;

import java.util.Arrays;

public class MainTest {

    private static final String DB_PATH   = "data/tasks.txt";

    private Either<String, Pair<String, String>> getTestCase() {

        try {
            var inPath = Path.of("tests/input.txt");
            var outPath = Path.of("tests/expected.txt");

            return Either.right(new Pair<>(
                Files.readString(inPath, Charset.forName("UTF-8")),
                Files.readString(outPath, Charset.forName("UTF-8")))
            );

        } catch (IOException e) {
            return Either.left(e.toString());
        }
    }

    @Test
    public void main_test() {
        var tasks = new TaskList(new Database(DB_PATH));
        var res = getTestCase();

        if (res.isLeft()) {
            fail(res.fromLeft());
        }

        var test = res.fromRight();
        var buffer = new ByteArrayOutputStream();

        var frontend = new TextFrontend(tasks,
            new Scanner(test.fst()),
            new PrintStream(buffer, /* autoFlush: */ true)
        );

        frontend.run();

        var output = buffer.toString().lines()
            .map(x -> x.stripTrailing())
            .reduce((a, b) -> a + '\n' + b)
            .get()
            + '\n';

        var x = output.getBytes();
        var y = test.snd().getBytes();

        System.err.printf("actual = %s\n\n", Arrays.toString(x));
        System.err.printf("expect = %s\n\n", Arrays.toString(y));

        if (!output.equals(test.snd())) {
            try {
                Files.write(Path.of("tests/actual.txt"), output.getBytes());
            } catch (IOException e) {
                System.err.printf("failed to write actual output: %s\n", e);
            }

            fail("output mismatch; actual output written to tests/actual.txt");
        }
    }
}
