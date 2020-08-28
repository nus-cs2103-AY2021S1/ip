package test.java;

import main.java.Deadline;
import main.java.Event;
import main.java.TextUi;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextUiTest {
    @Test
    public void testPrintEventWithNoEventTime() throws IllegalArgumentException {
        try {
            Event eventA = new Event("a", null);
            TextUi.printNewTasks("a");

        } catch (IllegalArgumentException exception) {
            String errorMessage = "Invalid input, no event time stated";
            assertEquals(errorMessage, exception.getMessage());
        }
    }
    @Test
    public void testPrintDeadlineWithNoName() throws IllegalArgumentException {
        try {
            Deadline deadlineA = new Deadline(null, null);
            TextUi.printNewTasks("deadline");

        } catch (IllegalArgumentException exception) {
            String errorMessage = "Invalid input, no deadline stated";
            assertEquals(errorMessage, exception.getMessage());
        }
    }
}