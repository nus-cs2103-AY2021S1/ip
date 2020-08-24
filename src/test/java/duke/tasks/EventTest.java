package duke.tasks;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testDateTimeFormat() throws DukeException {
        assertEquals("[E][0] concert at California (at: Jun 12 2020, 11.00pm - 12.00am)",
                Event.createEvent("concert at California", "2020-06-12 2300-0000").toString());
    }

}
