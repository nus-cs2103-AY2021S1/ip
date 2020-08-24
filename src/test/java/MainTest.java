// MainTest.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.


import java.util.Arrays;
import java.util.Scanner;
import java.util.Optional;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import ikura.Bot;
import ikura.Frontend;
import ikura.Database;
import ikura.TaskList;

import ikura.util.Pair;
import ikura.util.Either;

public class MainTest {

    private static final String BOT_NAME  = "ikurabowl";
    private static final String DB_PATH   = "data/tasks.txt";

    private Either<String, Pair<String, String>> getTestCase() {

        try {
            var inPath = Path.of("tests/input.txt");
            var outPath = Path.of("tests/expected.txt");

            return Either.right(new Pair<>(
                Files.readString(inPath),
                Files.readString(outPath))
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

        var ui = new Frontend(BOT_NAME,
            new Scanner(test.fst()),
            new PrintStream(buffer, /* autoFlush: */ true)
        );

        var bot = new Bot(ui, tasks);

        ui.greet();
        while (ui.readLine().map(bot::processCommand).orElse(false))
            ;

        var output = buffer.toString().lines()
            .map(x -> x.stripTrailing())
            .reduce((a, b) -> a + "\n" + b)
            .get()
            + "\n";

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
