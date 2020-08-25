package duke.command;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidCommandTest {

    @Test
    void testExecute() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Ensure something (anything) is printed
        new InvalidCommand("testing 123,./").execute();
        assertEquals("testing 123,./\r\n", outContent.toString());

        System.setOut(originalOut);
    }

}