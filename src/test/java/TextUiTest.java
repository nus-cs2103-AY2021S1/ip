import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextUiTest {
    @Test
    public void testPrintEventWithNoEventTime() throws IllegalArgumentException {
        try {
            Event eventA = new Event("a", null);
            TextUi.printEvent("a");

        } catch (IllegalArgumentException exception) {
            String errorMessage = "Invalid input, no event time stated";
            assertEquals(errorMessage, exception.getMessage());
        }
    }
    @Test
    public void testPrintDeadlineWithNoName() throws IllegalArgumentException {
        try {
            Deadline deadlineA = new Deadline(null, null);
            TextUi.printDeadline("deadline");

        } catch (IllegalArgumentException exception) {
            String errorMessage = "Invalid input, no deadline stated";
            assertEquals(errorMessage, exception.getMessage());
        }
    }
}