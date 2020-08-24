package command;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HelpCommandTest {

    @Test
    void isModifying_false() {
        assertFalse(new HelpCommand().hasUndo());
    }

    @Test
    void isExit_false() {
        assertFalse(new HelpCommand().isExit());
    }

    @Test
    void testExecute() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Ensure something (anything) is printed
        new HelpCommand().execute();
        assertNotEquals("", outContent.toString());

        System.setOut(originalOut);
    }
}