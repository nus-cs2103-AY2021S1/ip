package data.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    final LocalDateTime dateTime = LocalDateTime.parse("2020-10-18T14:30");
    final Event event = new Event("project meeting", dateTime);
    final Event completedEvent = new Event(true,"project meeting", dateTime);

    @Test
    void fileFormat() {
        assertEquals("E/X/project meeting/Sun, 18 Oct 2020 02:30 PM", event.fileFormat());
        assertEquals("E/O/project meeting/Sun, 18 Oct 2020 02:30 PM", completedEvent.fileFormat());
    }

    @Test
    void testToString() {
        assertEquals("[E][X] project meeting (at: Sun, 18 Oct 2020 02:30 PM)", event.toString());
        assertEquals("[E][O] project meeting (at: Sun, 18 Oct 2020 02:30 PM)", completedEvent.toString());
    }
}