package tickbot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTests {
    // Redirecting System.out and System.err
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    /**
     * Replaces system output streams with custom ones.
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Restores the system output streams.
     */
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Tests if the parser works.
     */
    @Test
    public void testParser() {
        Parser parser = new Parser();
        parser.executeCommand("done -1");
        String expected = "Sorry, no such task found.\n";
        assertEquals(expected, outContent.toString());
    }
}
