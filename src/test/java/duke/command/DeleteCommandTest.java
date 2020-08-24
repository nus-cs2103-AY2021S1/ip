package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
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
    public void testExecute() {
        TaskList tasks = new TaskList();
        Task task = new Todo("eat");
        tasks.addTask(task);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.execute(tasks, ui, storage);
        String expectedPrintStatement = "\t Noted. I've removed this duke.task:\n" +
                "\t   [T][✘] eat\n" +
                "\t Now you have 0 task in the list.\n";
        assertEquals(0, tasks.getNumberOfTask());
        assertEquals(expectedPrintStatement, OUT_CONTENT.toString());
    }
}

