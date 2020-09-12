package duke.inputoutput;

import static duke.utils.Messages.MESSAGE_GREETING;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputOutputTest {

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
        InputOutput inputOutput = new InputOutput();
        inputOutput.showLine();
        assertEquals(line, outContent.toString());
    }

    @Test
    public void testShowGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        InputOutput inputOutput = new InputOutput();
        inputOutput.showGreeting();
        String expected = "Hello from\n" + logo + "\n" + line
                + MESSAGE_GREETING + "\n" + line;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testShowBye() {
        String expected = line + "\t Bye. Hope to see you again soon!\n" + line;
        InputOutput inputOutput = new InputOutput();
        inputOutput.showBye();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testShow() {
        String message = "\t test message";
        InputOutput inputOutput = new InputOutput();
        inputOutput.show(message);
        String expected = line + message + "\n" + line;
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void readCommand_nonEmptyInput_returnInput() {
        String input = "test command";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputOutput inputOutput = new InputOutput();
        assertEquals(input, inputOutput.readCommand());
    }

    @Test
    public void readCommand_emptyInput_returnBye() {
        String input = "";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputOutput inputOutput = new InputOutput();
        assertEquals("bye", inputOutput.readCommand());
    }

}
