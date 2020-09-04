import duke.Ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void readCommand() {
        String input = "return one\nreturn two\nreturn three";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Ui ui = new Ui();
        assertEquals("return one", ui.readCommand());
        assertEquals("return two", ui.readCommand());
        assertEquals("return three", ui.readCommand());
    }

    @Test
    void printMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        ui.printMessage("test print");
        assertEquals("test print\n", outContent.toString());
    }
}