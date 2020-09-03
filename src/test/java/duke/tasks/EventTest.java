package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.utils.DukeDateTime;

public class EventTest {

    @Test
    public void testGetTime() {
        DukeDateTime at = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Event event = new Event(description, at);
        assertEquals(at, event.getTime());
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
    public void toFileString_markedAsDone() {
        DukeDateTime at = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Event event = new Event(description, at);
        event.markAsDone();
        String expected = "E | 1 | " + description + " | 23 Aug 2020 1:00 PM";
        assertEquals(expected, event.toFileString());
    }

    @Test
    public void toString_markedAsDone() {
        DukeDateTime at = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Event event = new Event(description, at);
        event.markAsDone();
        String expected = "[E][\u2713] " + description + " (at: 23 Aug 2020 1:00 PM)";
        assertEquals(expected, event.toString());
    }
}
