package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exceptions.NoSuchTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

public class DoneCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String line = "\t" + "_".repeat(75) + "\n";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void execute_validInput_success() {
        Todo todo = new Todo("description");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        DoneCommand command = new DoneCommand(0);
        command.execute(taskList, new Ui());
        String expected = line + "\t Nice! I've marked this task as done:\n\t\t" + todo.toString() + "\n" + line;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void execute_invalidInput_exceptionThrown() {
        try {
            DoneCommand command = new DoneCommand(0);
            command.execute(new TaskList(), new Ui());
            fail();
        } catch (NoSuchTaskException e) {
            assertEquals("OOPS! No such task exists!", e.getMessage());
        }

    }
}
