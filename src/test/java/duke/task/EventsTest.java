package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventsTest {

    @Test
    void returnTime() {
        assertEquals("2020-02-12 1330",
                new Events("party", "2020-02-12 1330").returnTime());
    }

    @Test
    void doneTask() {
        assertEquals("[E][\u2713] party (at: 12 Feb 2020 1:30 PM)",
                new Events("party", "2020-02-12 1330").doneTask().toString());
    }

    @Test
    void testToString() {
        assertEquals("[E][\u274C] party (at: 12 Feb 2020 1:30 PM)",
                new Events("party", "2020-02-12 1330").toString());
    }
}
