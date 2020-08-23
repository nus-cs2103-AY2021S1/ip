package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestDoneCommand {

    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final PrintStream ORIGINAL_OUT = System.out;
    private final String LINE = "\t" + "_".repeat(75) + "\n";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void execute_validInput_success() {
        Todo todo = new Todo("description");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        DoneCommand command = new DoneCommand(0);
        command.execute(taskList, new Ui());
        String expected = LINE + "\t Nice! I've marked this task as done:\n\t\t" + todo.toString() + "\n" + LINE;
        assertEquals(expected, OUT_CONTENT.toString());
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
