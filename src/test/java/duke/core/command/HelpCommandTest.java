package duke.core.command;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class HelpCommandTest {

    @Test
    void execute_outputExpected() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Ensure something (anything) is printed
        new HelpCommand().execute();
        assertNotEquals("", outContent.toString());

        System.setOut(originalOut);
    }
}
