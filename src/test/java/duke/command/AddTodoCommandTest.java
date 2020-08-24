package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Todo;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTodoCommandTest {
    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final PrintStream ORIGINAL_OUT = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void testExecute() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddTodoCommand addTodoCommand = new AddTodoCommand("eat");
        addTodoCommand.execute(tasks, ui, storage);
        String expectedPrintStatement = "\t Got it. I've added this todo: \n\t   "
                + "[T][✘] eat\n\t "
                + "Now you have 1 task in the list.\n";
        assertEquals("[T][✘] eat", tasks.getTask(0).toString());
        assertEquals(expectedPrintStatement, OUT_CONTENT.toString());
    }
}
