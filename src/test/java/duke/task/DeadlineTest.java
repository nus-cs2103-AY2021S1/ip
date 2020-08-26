package duke.task;

import duke.exception.IncorrectFormatException;
import duke.ui.UIPrint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void createDeadline_normalInput_success() {
        String input01 = "read book /by tmr";
        String input02 = "return book /by 18:00";
        String input03 = "have dinner /by 2020-08-26 12:00";

        try {
            Deadline.createDeadline(input01);
            Deadline.createDeadline(input02);
            Deadline.createDeadline(input03);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void createDeadline_noDeadline_exceptionThrown() {
        Exception exception = assertThrows(IncorrectFormatException.class,
                () -> Deadline.createDeadline("read book"));

        String line = UIPrint.getLine(UIPrint.star, 50);
        String errMessage =
                line + "\nPlease follow the format of deadline <duke.task description> /by <deadline>\n" + line;

        assertEquals(errMessage, exception.getMessage());
    }

    @Test
    public void getTaskTypeTest() {
        assertEquals("deadline",
                Deadline.createDeadline("read book /by tmr").getTaskType());
    }
}
