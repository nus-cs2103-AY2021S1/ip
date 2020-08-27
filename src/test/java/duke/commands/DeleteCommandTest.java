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

public class DeleteCommandTest {

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
    public void execute_validIndex_success() {
        DeleteCommand command = new DeleteCommand(0);
        TaskList taskList = new TaskList();
        Todo todo = new Todo("description");
        taskList.addTask(todo);
        command.execute(taskList, new Ui());
        String expected = line + "\t Noted. I've removed this task:\n\t\t"
                + todo.toString()
                + "\n\t "
                + taskList.tasksRemaining()
                + "\n"
                + line;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        try {
            DeleteCommand command = new DeleteCommand(0);
            command.execute(new TaskList(), new Ui());
            fail();
        } catch (NoSuchTaskException e) {
            assertEquals("OOPS! No such task exists!", e.getMessage());
        }
    }
}
