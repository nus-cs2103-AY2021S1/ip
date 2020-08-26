package duke.task;

import duke.exception.IncorrectFormatException;
import duke.ui.UIPrint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {

    @Test
    public void createEvent_normalInput_success() {
        String input01 = "read book /at tmr";
        String input02 = "return book /at 18:00";
        String input03 = "have dinner /at 2020-08-26 12:00";

        try {
            Event.createEvent(input01);
            Event.createEvent(input02);
            Event.createEvent(input03);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void createDeadline_noTime_exceptionThrown() {
        Exception exception = assertThrows(IncorrectFormatException.class,
                () -> Event.createEvent("read book"));

        String line = UIPrint.getLine(UIPrint.star, 50);
        String errMessage =
                line + "\nPlease follow the format of event <duke.task description> /at <event duke.time>\n" + line;

        assertEquals(errMessage, exception.getMessage());
    }

    @Test
    public void getTaskTypeTest() {
        assertEquals("event",
                Event.createEvent("read book /at tmr").getTaskType());
    }
}
