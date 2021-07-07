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

public class DoneCommandTest {
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
        Ui ui = new Ui();
        Storage storage = new Storage();
        taskManager.addTask(testTodo);
        DoneCommand doneCommandTest = new DoneCommand(1);
        doneCommandTest.executeCommand(taskManager, ui, storage);
        String expectedOutput = leftPadding + line
                + leftPadding + "Good job on completing this task! I have marked this task as done: " + "\n"
                + leftPadding + Colour.Green(testTodo.toString()) + "\n"
                + leftPadding + line;
        assertEquals(expectedOutput, outContent.toString());
    }
}
