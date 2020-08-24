package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString_doneEvent() {
        assertEquals("[E][\u2713] Submit homework (at: Aug 29 2020 1200)",
                new Event("Submit homework", "Aug 29 2020 1200", true).toString());
    }

    @Test
    void testToString_undoneEvent() {
        assertEquals("[E][\u2718] Submit essay (at: Aug 29 2020 2359)",
                new Event("Submit essay", "Aug 29 2020 2359", false).toString());
    }
}