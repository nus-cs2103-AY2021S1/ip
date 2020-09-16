package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    void constructorTest_startAfterEndDate_exceptionThrown() {
        try {
            new Event("Test", "02/01/2019-01/01/2019 0700-1500");
            fail();
        } catch (DukeException e) {
            assertEquals("the start date cannot be after the end date.", e.getMessage());
        }
    }

    @Test
    void constructorTest_startAfterEndTime_exceptionThrown() {
        try {
            new Event("Test", "01/01/2020 1400-0700");
            fail();
        } catch (DukeException e) {
            assertEquals("the start time cannot be after the end time.", e.getMessage());
        }
    }

    @Test
    void stringConversion_onSameDate() throws DukeException {
        Event event = new Event("Test", "01/01/2020 0700-1400");
        assertEquals("[E][✘][4] Test (at: Wed, 1 January 2020, 7:00AM - 2:00PM)", event.toString());
    }

    @Test
    void stringConversion_onDifferentDate() throws DukeException {
        Event event = new Event("Test", "01/01/2020-02/01/2020 0700-1400");
        assertEquals("[E][✘][4] Test (at: Wed, 1 January 2020 - Thu, 2 January 2020, 7:00AM - 2:00PM)",
                event.toString());
    }

    @Test
    void serializeTest() throws DukeException {
        Event event = new Event("Test", "01/01/2020-02/01/2020 0700-1400");
        assertEquals("E | 0 | 4 | Test | 01/01/2020-02/01/2020 0700-1400", event.serialize());
    }
}
