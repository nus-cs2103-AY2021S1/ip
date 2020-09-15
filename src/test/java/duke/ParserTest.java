package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        parser.run("deadline return book /by 2019-10-15");
    }

    @org.junit.jupiter.api.Test
    void testRunEvent1() {
        parser.run("event return book /at 2019-10-15");
    }

    @org.junit.jupiter.api.Test
    void testRunEvent2() {
        System.out.println(parser.run("event return book /between 2019-10-15 and 2019-10-16"));
    }

    @org.junit.jupiter.api.Test
    void testRunTodo1() {
        parser.run("todo return book");
    }

    static String readFile(String filename) throws IOException {
        ClassLoader classLoader = ParserTest.class.getClassLoader();
        return Files.readString(Path.of(classLoader.getResource("file/" + filename).getFile()));
    }

    @Test
    void testRunAll1() throws IOException {
        String input = ParserTest.readFile("input1.txt");
        String[] lineInput = input.split("\n");
        String expectedOutput = ParserTest.readFile("output1.txt");
        StringBuilder output = new StringBuilder();
        for (String line : lineInput) {
            output.append(parser.run(line));
        }
        Assertions.assertEquals(expectedOutput, output.toString());
    }
}
