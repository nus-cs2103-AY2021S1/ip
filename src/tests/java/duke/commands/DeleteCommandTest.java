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

public class DeleteCommandTest {
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
    public void executeCommand_validTaskIndex_success() throws InvalidFilePathException {
        Todo testTodo = new Todo("Test");
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(testTodo);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DeleteCommand deleteCommandTest = new DeleteCommand(1);
        deleteCommandTest.executeCommand(taskManager, ui, storage);
        String expectedOutput = leftPadding + line
                + leftPadding + "Noted. I have removed the task: " + "\n"
                + leftPadding + Colour.Red(testTodo.toString()) + "\n"
                + leftPadding + line;
        assertEquals(expectedOutput, outContent.toString());
    }
}
