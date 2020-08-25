package duke.commands;


import duke.exceptions.InvalidFilePathException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.task.Todo;
import duke.utils.Colour;
import duke.utils.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ListTaskCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String line = "_".repeat(80) + "\n";
    private final String leftPadding = " ".repeat(7);

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testExecuteCommand() throws InvalidFilePathException {
        TaskManager taskManager = new TaskManager();
        Ui ui = new Ui();
        Storage storage = new Storage();
        Todo testTodo = new Todo("Test");
        taskManager.addTask(testTodo);
        ListTasksCommand testListTasksCommand = new ListTasksCommand();
        testListTasksCommand.executeCommand(taskManager, ui, storage);
        String expectedOutput = leftPadding + line
                + leftPadding + "Here are all your tasks: " + "\n"
                + leftPadding + "1) [T][\u2718] Test\n"
                + leftPadding + "You have completed " + Colour.Green("0 task") + " and have yet to complete "
                + Colour.Red("1 task") + "\n"
                + leftPadding + line;
        assertEquals(expectedOutput, outContent.toString());
    }


}
