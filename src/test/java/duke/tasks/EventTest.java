package duke.tasks;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventTest {

    @Test
    public void testCreateEvent() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10");
    }

    @Test
    public void testEventSetComplete() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10");
        event.setComplete(true);
        assertTrue(event.isComplete);
    }

    @Test
    public void testIncompleteEventToString() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10");
        assertEquals("[E][X] Test content (at: 1990-10-10 10:10)", event.toString());
    }

    @Test
    public void testCompleteEventToString() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10");
        event.setComplete(true);
        assertEquals("[E][Y] Test content (at: 1990-10-10 10:10)", event.toString());
    }

    @Test
    public void testIncompleteEventToSaveString() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10");
        assertEquals("E/0/Test content/1990-10-10 10:10", event.toSaveString());
    }

    @Test
    public void testCompleteEventToSaveString() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10");
        event.setComplete(true);
        assertEquals("E/1/Test content/1990-10-10 10:10", event.toSaveString());
    }

}
