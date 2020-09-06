package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DeadlineTest {
    private final Deadline deadline;

    public DeadlineTest() throws DukeException {
        deadline = new Deadline("test", "2020-08-24");
    }

    @Test
    void testTimeWrongFormat_ThrowsException() {
        assertThrows(DukeException.class, () ->
            new Deadline("test", "bad input"));
    }

    @Test
    void testIdentifier_CorrectOutput() {
        assertEquals("D", deadline.getTaskIdentifier());
    }

    @Test
    void testToString_CorrectOutput() {
        assertEquals("[D][ ] test (by: Aug 24 2020)", deadline.toString());
    }

    @Test
    void testMarkAsDone_ChangesOutput() {
        deadline.markAsDone();
        assertEquals("[D][X] test (by: Aug 24 2020)", deadline.toString());
    }
}
