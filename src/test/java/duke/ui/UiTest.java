package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private static final String LINE = "\t____________________________________________________________";
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
    public void testSendGreeting() {
        String logo = " ____        _        \n\t "
                + "|  _ \\ _   _| | _____ \n\t "
                + "| | | | | | | |/ / _ \\\n\t "
                + "| |_| | |_| |   <  __/\n\t "
                + "|____/ \\__,_|_|\\_\\___|\n\t ";
        String expected = LINE + "\n\t " + logo
                + "\n\t Hello! I'm Duke\n\t What can I do for you?\n" + LINE + "\n";
        Ui ui = new Ui();
        ui.sendGreeting();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testShowLine() {
        String expected = LINE + "\n";
        Ui ui = new Ui();
        ui.showLine();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testShowError() {
        Exception e = new FileNotFoundException();
        String expected = "\t " + e.getMessage() + "\n";
        Ui ui = new Ui();
        ui.showError(e);
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testPrintMessage() {
        String expected = "\t HELLO\n";
        Ui ui = new Ui();
        String message = "HELLO";
        ui.printMessage(message);
        assertEquals(expected, outContent.toString());
    }
}
