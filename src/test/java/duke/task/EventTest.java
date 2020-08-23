package duke.task;

import duke.exception.InvalidDateInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void new_validDate_noExceptionThrown() {
        try {
            new Event("desc", "2020-10-30");
        } catch (InvalidDateInputException e) {
            fail();
        }
    }

    @Test
    public void new_invalidDate_exceptionThrown() {
        try {
            new Event("desc", "30-10-2020");
            fail();
        } catch (InvalidDateInputException e) {
            assertEquals("☹ OOPS!!! 30-10-2020 is not a recognised date format. Please key in " +
                    "dates in the format yyyy-MM-dd. For example, 2007-12-03.", e.getMessage());
        }
    }

    @Test
    public void serialise_uncompletedEvent_success() throws InvalidDateInputException {
        Event event = new Event("event description", "2020-10-30");

        assertEquals("E | 0 | event description | 2020-10-30", event.serialise());
    }

    @Test
    public void serialise_completedEvent_success() throws InvalidDateInputException {
        Event event = new Event("event description", "2020-10-30", true);

        assertEquals("E | 1 | event description | 2020-10-30", event.serialise());
    }
}
