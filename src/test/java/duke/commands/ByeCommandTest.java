package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommandTest {

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
    public void testExecute() {
        ByeCommand command = new ByeCommand();
        command.execute(new TaskList(), new Ui());
        String expected = line + "\t Bye. Hope to see you again soon!\n" + line;
        assertEquals(expected, outContent.toString());
    }
}
