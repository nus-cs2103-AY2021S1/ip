package farrell.duke;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TestManager {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    public void init() {
        System.setOut(new PrintStream(outContent));
        try {
            Files.deleteIfExists(Paths.get("data", "data.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cleanUp() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    public void giveInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public String getOutput() {
        return outContent.toString();
    }
}
