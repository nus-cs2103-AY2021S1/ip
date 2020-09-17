package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

class EventTest {

    @Test
    public void testCreateEvent() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10", "0");
    }

    @Test
    public void testEventSetComplete() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10", "0");
        event.setComplete(true);
        assertTrue(event.isComplete);
    }

    @Test
    public void testIncompleteEventToString() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10", "0");
        assertEquals("[E][X](!) Test content (at: 1990-10-10 10:10)", event.toString());
    }

    @Test
    public void testCompleteEventToString() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10", "0");
        event.setComplete(true);
        assertEquals("[E][Y](!) Test content (at: 1990-10-10 10:10)", event.toString());
    }

    @Test
    public void testIncompleteEventToSaveString() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10", "0");
        assertEquals("E/0/0/Test content/1990-10-10 10:10", event.toSaveString());
    }

    @Test
    public void testCompleteEventToSaveString() throws DukeException {
        Event event = new Event("Test content", "1990-10-10 10:10", "0");
        event.setComplete(true);
        assertEquals("E/0/1/Test content/1990-10-10 10:10", event.toSaveString());
    }

}
