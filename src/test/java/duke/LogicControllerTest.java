package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LogicControllerTest {
    private static LogicController logicController;

    @BeforeAll
    static void initAll() {
        Ui.init();
    }

    @BeforeEach
    void init() {
        logicController = new LogicController(new TaskList());
    }

    @Test
    void testRunDeadline1() {
        String response = logicController.run("deadline return book /by 2019-10-15");
        Assertions.assertTrue(response.contains("[D][✗] return book (by: Oct 15 2019)"));
    }

    @Test
    void testRunEvent1() {
        String response = logicController.run("event return book /at 2019-10-15");
        Assertions.assertTrue(response.contains("[E][✗] return book (at: Oct 15 2019)"));
    }

    @Test
    void testRunEvent2() {
        String response = logicController.run("event return book /between 2019-10-15 and 2019-10-16");
        Assertions.assertTrue(response.contains("[E][✗] return book (between: Oct 15 2019 and Oct 16 2019)"));
    }

    @Test
    void testRunEvent3() {
        String response = logicController.run("event return book /at 06/09/2019");
        Assertions.assertTrue(response.contains("[E][✗] return book (at: Sep 6 2019)"));
    }

    @Test
    void testRunTodo1() {
        String response = logicController.run("todo return book");
        Assertions.assertTrue(response.contains("[T][✗] return book"));
    }

    @Test
    void testRunEventBetweenMissingArgument() {
        String response = logicController.run("event return book /between 2019-10-15");
        Assertions.assertTrue(response.contains("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
    }

    @Test
    void testRunEventAtMissingArgument() {
        String response = logicController.run("event return book /at");
        Assertions.assertTrue(response.contains("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
    }

    public void testRunAll1() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        String input = Files.readString(Path.of(classLoader.getResource("file/" + "input1.txt").getFile()));
        String expectedOutput = Files.readString(Path.of(classLoader.getResource("file/" + "output1.txt").getFile()));
        String[] lineInput = input.split("\n");
        StringBuilder output = new StringBuilder();
        for (String s : lineInput) {
            output.append(logicController.run(s));
        }
        Assertions.assertEquals(expectedOutput, output.toString());
    }

    @Test
    public void testRunAll2() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        String input = Files.readString(Path.of(classLoader.getResource("file/" + "input2.txt").getFile()));
        String expectedOutput = Files.readString(Path.of(classLoader.getResource("file/" + "output2.txt").getFile()));
        String[] lineInput = input.split("\n");
        StringBuilder output = new StringBuilder();
        for (String s : lineInput) {
            output.append(logicController.run(s));
        }
        Assertions.assertEquals(expectedOutput, output.toString());
    }
}

