package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

class DeadlineTest {

    @Test
    void new_validDateTime_noExceptionThrown() {
        try {
            new Deadline("test desc", "2020-08-25 22:00");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void new_invalidDateTime_exceptionThrown() {
        try {
            new Deadline("test desc", "2020-8-25 2359");
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please input date and time in correct format:"
                    + " 'yyyy-MM-dd HH:MM' (24-hour time format).", e.getMessage());
        }
    }
}

