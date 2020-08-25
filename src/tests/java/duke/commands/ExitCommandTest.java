package duke.commands;

import duke.exceptions.InvalidFilePathException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.utils.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
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
        String expectedOutput = leftPadding + line
                + leftPadding + "Bye. Hope my service has been satisfactory. Hope to see you again soon." + "\n"
                + leftPadding + line;
        ExitCommand exitTest = new ExitCommand();
        exitTest.executeCommand(taskManager, ui, storage);
        assertEquals(expectedOutput, outContent.toString());
    }
}
