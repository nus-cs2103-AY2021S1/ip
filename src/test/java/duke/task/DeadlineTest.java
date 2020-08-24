package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exception.DukeException;

public class DeadlineTest {
    private final Deadline deadline;

    public DeadlineTest() throws DukeException {
        deadline = new Deadline("test", "2020-08-24");
    }

    @Test
    void testRejectsBadInputs() {
        assertThrows(DukeException.class, () ->
                new Deadline("test", "bad input"));
    }

    @Test
    void testCorrectIdentifier() {
        assertEquals("D", deadline.getTaskIdentifier());
    }

    @Test
    void testToString() {
        assertEquals("[D][ ] test (by: Aug 24 2020)", deadline.toString());
    }

    @Test
    void testMarkAsDone() {
        deadline.markAsDone();
        assertEquals("[D][X] test (by: Aug 24 2020)", deadline.toString());
    }
}
