package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private final String line = "\t" + "_".repeat(75) + "\n";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testShowLine() {
        Ui ui = new Ui();
        ui.showLine();
        assertEquals(line, outContent.toString());
    }

    @Test
    public void testShowGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Ui ui = new Ui();
        ui.showGreeting();
        String expected = "Hello from\n" + logo + "\n" + line
            + "\t Hello! I'm Duke\n\t What can I do for you?\n" + line;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testShowBye() {
        String expected = line + "\t Bye. Hope to see you again soon!\n" + line;
        Ui ui = new Ui();
        ui.showBye();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testShow() {
        String message = "\t test message";
        Ui ui = new Ui();
        ui.show(message);
        String expected = line + message + "\n" + line;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void readCommand_nonEmptyInput_returnInput() {
        String input = "test command";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();
        assertEquals(input, ui.readCommand());
    }

    @Test
    public void readCommand_emptyInput_returnBye() {
        String input = "";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();
        assertEquals("bye", ui.readCommand());
    }

}
