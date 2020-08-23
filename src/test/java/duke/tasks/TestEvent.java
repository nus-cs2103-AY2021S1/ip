package duke.tasks;

import duke.utils.DukeDateTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEvent {

    @Test
    public void testGetAt() {
        DukeDateTime at = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Event event = new Event(description, at);
        assertEquals(at, event.getAt());
    }

    @Test
    public void testToFileString() {
        DukeDateTime at = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Event event = new Event(description, at);
        String expected = "E | 0 | " + description + " | 23 Aug 2020 1:00 PM";
        assertEquals(expected, event.toFileString());
    }

    @Test
    public void testToString() {
        DukeDateTime at = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Event event = new Event(description, at);
        String expected = "[E][\u2718] " + description + " (at: 23 Aug 2020 1:00 PM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToFileString_markedAsDone() {
        DukeDateTime at = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Event event = new Event(description, at);
        event.markAsDone();
        String expected = "E | 1 | " + description + " | 23 Aug 2020 1:00 PM";
        assertEquals(expected, event.toFileString());
    }

    @Test
    public void testToString_markedAsDone() {
        DukeDateTime at = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Event event = new Event(description, at);
        event.markAsDone();
        String expected = "[E][\u2713] " + description + " (at: 23 Aug 2020 1:00 PM)";
        assertEquals(expected, event.toString());
    }
}
