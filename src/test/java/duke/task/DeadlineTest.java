package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyTimeException;
import duke.exception.ExceptionMessage;
import duke.exception.IncorrectFormatException;

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
    public void createDeadline_incorrectFormat_exceptionThrown() {
        String incorrectFormatInput = "read book";
        // lambda used here
        Exception exception = assertThrows(
                IncorrectFormatException.class, () -> Deadline.createDeadline(incorrectFormatInput));

        String errMessage = ExceptionMessage.DEADLINE_INCORRECT_FORMAT_MESSAGE;

        assertEquals(errMessage, exception.getMessage());
    }

    @Test
    public void createDeadline_blankTime_exceptionThrown() {
        String blankTimeInput = "read book /by      ";

        Exception exception = assertThrows(
                EmptyTimeException.class, () -> Deadline.createDeadline(blankTimeInput));

        String errMessage = ExceptionMessage.EMPTY_TIME_MESSAGE;

        assertEquals(errMessage, exception.getMessage());
    }

    @Test
    public void getTaskTypeTest() {
        assertEquals("deadline",
                Deadline.createDeadline("read book /by tmr").getTaskType());
    }
}
