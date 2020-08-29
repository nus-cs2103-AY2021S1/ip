package farrell.duke;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestManager {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    /**
     * Initialize the test manager to redirect input and output
     */
    public void init() {
        System.setOut(new PrintStream(outContent));
        try {
            Files.deleteIfExists(Paths.get("data", "data.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the input and output to their original sources
     */
    public void cleanUp() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * Simulates entering input into System.in.
     * @param input The input string to give to System.in.
     */
    public void giveInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    /**
     * Retrieves output from System.out.
     * @return The output string from System.out.
     */
    public String getOutput() {
        return outContent.toString();
    }
}
