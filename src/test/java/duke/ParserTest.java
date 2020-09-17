package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class ParserTest {
    private static Parser parser;

    @BeforeAll
    static void initAll() {
        Ui.init();
    }

    @BeforeEach
    void init() {
        parser = new Parser(new TaskList());
    }

    @org.junit.jupiter.api.Test
    void testRunDeadline1() {
        String response = parser.run("deadline return book /by 2019-10-15");
        Assertions.assertTrue(response.contains("[D][✗] return book (by: Oct 15 2019"));
    }

    @org.junit.jupiter.api.Test
    void testRunEvent1() {
        String response = parser.run("event return book /at 2019-10-15");
        Assertions.assertTrue(response.contains("[E][✗] return book (at: Oct 15 2019"));
    }

    @org.junit.jupiter.api.Test
    void testRunEvent2() {
        String response = parser.run("event return book /between 2019-10-15 and 2019-10-16");
        Assertions.assertTrue(response.contains("[E][✗] return book (between: Oct 15 2019 and Oct 16 2019)"));
    }

    @org.junit.jupiter.api.Test
    void testRunTodo1() {
        String response = parser.run("todo return book");
        Assertions.assertTrue(response.contains("[T][✗] return book"));
    }

    @org.junit.jupiter.api.Test
    void testRunEventBetweenMissingArgument() {
        String response = parser.run("event return book /between 2019-10-15");
        Assertions.assertTrue(response.contains("☹ OOPS!!! Invalid dates provided"));
    }

    @org.junit.jupiter.api.Test
    void testRunEventAtMissingArgument() {
        String response = parser.run("event return book /at");
        Assertions.assertTrue(response.contains("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
    }

    @org.junit.jupiter.api.Test
    public void testRunAll1() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        String input = Files.readString(Path.of(classLoader.getResource("file/" + "input1.txt").getFile()));
        System.out.println(input);
        String[] lineInput = input.split("\n");
        String expectedOutput = Files.readString(Path.of(classLoader.getResource("file/" + "output1.txt").getFile()));
        StringBuilder output = new StringBuilder();
        for (String s : lineInput) {
            output.append(parser.run(s));
        }
        Assertions.assertEquals(expectedOutput, output.toString());
    }
}

