package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    //@@author Jonathan Cook
    // Reused from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Task task;
    @BeforeEach
    void init() {
        task = new Task("hello world", false);
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void testMarkAsDone() {
        task.markAsDone();
        String str = "Nice! I've marked this duke.task as done:\n[\u2713] hello world";
        assertEquals(str, outputStreamCaptor.toString().trim());
    }

    @Test
    public void formatStyling() {
        String str = ",hello world,0\n";
        assertEquals(str, task.formatStyling());
    }
}
