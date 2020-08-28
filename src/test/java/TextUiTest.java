import org.junit.jupiter.api.Test;

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