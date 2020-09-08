package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void execute_validKeyword_printsTasksMatchingKeyword() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("hello"));
        tasks.add(new ToDo("hello cat"));
        tasks.add(new ToDo("dog walk"));
        tasks.add(new ToDo("dog dance"));
        new FindCommand("find dog").execute(tasks);
        assertEquals("1. [T][ ] dog walk\n" +
                "2. [T][ ] dog dance\n", outContent.toString());
    }
}