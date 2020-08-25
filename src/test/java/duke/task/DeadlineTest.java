package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DeadlineTest {

    @Test
    void newDeadline_ValidDateTime_noExceptionThrown() {
        try {
            new Deadline("test desc", "2020-08-25 22:00");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void newDeadline_InvalidDateTime_ExceptionThrown() {
        try {
            new Deadline("test desc", "2020-8-25 2359");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Please input date and time in correct format: " +
                    "'yyyy-MM-dd HH:MM' (24-hour time format).", e.getMessage());
        }
    }
}