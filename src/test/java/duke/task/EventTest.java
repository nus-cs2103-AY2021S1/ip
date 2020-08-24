package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class EventTest {
    private final Event event;

    public EventTest() throws DukeException {
        event = new Event("test", "2020-08-24");
    }

    @Test
    void testRejectsBadInputs() {
        assertThrows(DukeException.class, () ->
            new Event("test", "bad input"));
    }

    @Test
    void testCorrectIdentifier() {
        assertEquals("E", event.getTaskIdentifier());
    }

    @Test
    void testToString() {
        assertEquals("[E][ ] test (at: Aug 24 2020)", event.toString());
    }

    @Test
    void testMarkAsDone() {
        event.markAsDone();
        assertEquals("[E][X] test (at: Aug 24 2020)", event.toString());
    }
}
