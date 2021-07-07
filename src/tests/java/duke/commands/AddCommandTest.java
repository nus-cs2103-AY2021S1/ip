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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
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
    public void testExecuteCommandTest() throws InvalidFilePathException {
        TaskManager taskManager = new TaskManager();
        Ui ui = new Ui();
        Storage storage = new Storage();
        Todo testTodo = new Todo("Read book");
        AddCommand addCommand = new AddCommand(testTodo);
        addCommand.executeCommand(taskManager, ui, storage);
        String expectedTextOutput = leftPadding + line
                + leftPadding + "Got it. I've added this task:\n "
                + leftPadding + Colour.Green(testTodo.toString()) + "\n"
                + leftPadding + "Now you have a total of 1 task in the list." + "\n"
                + leftPadding + line;
        assertEquals(expectedTextOutput, outContent.toString());
    }
}
