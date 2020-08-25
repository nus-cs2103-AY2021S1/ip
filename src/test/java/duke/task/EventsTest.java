package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventsTest {

    @Test
    void returnTime() {
        assertEquals("2020-02-12 1330",
                new Events("party", "2020-02-12 1330").returnTime());
    }

    @Test
    void doneTask() {
        assertEquals("[E][✓] party (at: 12 Feb 2020 1:30 PM)",
                new Events("party", "2020-02-12 1330").doneTask().toString());
    }

    @Test
    void testToString() {
        assertEquals("[E][✗] party (at: 12 Feb 2020 1:30 PM)",
                new Events("party", "2020-02-12 1330").toString());
    }
}