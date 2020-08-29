package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Task task = new Todo("eat");
        tasks.addTask(task);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DoneCommand doneCommand = new DoneCommand(0);
        doneCommand.execute(tasks, ui, storage);
        String expectedPrintStatement = "\t Nice! I've marked this task as done:\n"
                + "\t   [T][✓] eat\n";
        assertEquals(1, tasks.getNumberOfTask());
        assertEquals(expectedPrintStatement, outContent.toString());
    }
}

