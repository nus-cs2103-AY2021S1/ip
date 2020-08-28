package duke.Ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private Ui ui;
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void initStream() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(systemOut);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    void readCommand_withTrailingSpaces() {
        String data = "Test   ";
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        ui = new Ui();
        assertEquals("Test", ui.readCommand());
        System.setIn(systemIn);
    }

    @Test
    void showLine() {
        ui = new Ui();
        ui.showLine();
        assertEquals(System.lineSeparator(), getOutput());
    }
}
