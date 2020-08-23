package duke.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final PrintStream ORIGINAL_OUT = System.out;
    private final InputStream ORIGINAL_IN = System.in;

    private final String LINE = "\t" + "_".repeat(75) + "\n";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
        System.setIn(ORIGINAL_IN);
    }

    @Test
    public void testShowLine() {
        Ui ui = new Ui();
        ui.showLine();
        assertEquals(LINE, OUT_CONTENT.toString());
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
        String expected = "Hello from\n" +
                logo +
                "\n" +
                LINE +
                "\t Hello! I'm Duke\n\t What can I do for you?\n" +
                LINE;
        assertEquals(expected, OUT_CONTENT.toString());
    }

    @Test
    public void testShowBye() {
        String expected = LINE + "\t Bye. Hope to see you again soon!\n" + LINE;
        Ui ui = new Ui();
        ui.showBye();
        assertEquals(expected, OUT_CONTENT.toString());
    }

    @Test
    public void testShow() {
        String message = "\t test message";
        Ui ui = new Ui();
        ui.show(message);
        String expected = LINE + message + "\n" + LINE;
        assertEquals(expected, OUT_CONTENT.toString());
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
