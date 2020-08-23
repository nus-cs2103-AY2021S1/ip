package duke.task;

import duke.exception.InvalidDateInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void new_validDate_noExceptionThrown() {
        try {
            new Deadline("desc", "2020-10-30");
        } catch (InvalidDateInputException e) {
            fail();
        }
    }

    @Test
    public void new_invalidDate_exceptionThrown() {
        try {
            new Deadline("desc", "30-10-2020");
            fail();
        } catch (InvalidDateInputException e) {
            assertEquals("☹ OOPS!!! 30-10-2020 is not a recognised date format. Please key in " +
                    "dates in the format yyyy-MM-dd. For example, 2007-12-03.", e.getMessage());
        }
    }


    @Test
    public void serialise_uncompletedDeadline_success() throws InvalidDateInputException {
        Deadline deadline = new Deadline("deadline description", "2020-10-30");

        assertEquals("D | 0 | deadline description | 2020-10-30", deadline.serialise());
    }

    @Test
    public void serialise_completedDeadline_success() throws InvalidDateInputException {
        Deadline deadline = new Deadline("deadline description", "2020-10-30", true);

        assertEquals("D | 1 | deadline description | 2020-10-30", deadline.serialise());
    }
}
