package duke.tasks;

import duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDateTimeFormat() throws DukeException {
        assertEquals("[D][0] homework (by: Dec 12 2020, 6.00pm)",
                Deadline.createDeadline("homework", "2020-12-12 1800").toString());
    }

    @Test
    public void testStoredStringFormat() throws DukeException {
        assertEquals("D@0@homework@2020-12-12@1800",
                Deadline.createDeadline("homework", "2020-12-12 1800").storedTaskString());
    }

}
