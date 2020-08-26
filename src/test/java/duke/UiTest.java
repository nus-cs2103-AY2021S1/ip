package duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void greet_success() {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        new Ui().greet();
        assertEquals("Hello! I'm Duke\nWhat can I do for you?", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    public void sendBar_success() {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        new Ui().sendBar();
        assertEquals("____________________________________________________________", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }
}