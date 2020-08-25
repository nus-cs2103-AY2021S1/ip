package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlinesTest {

    @Test
    void returnTime() {
        assertEquals("2020-02-12 1330",
                new Deadlines("party", "2020-02-12 1330").returnTime());
    }

    @Test
    void doneTask() {
        assertEquals("[D][✓] party (by: 12 Feb 2020 1:30 PM)",
                new Deadlines("party", "2020-02-12 1330").doneTask().toString());
    }

    @Test
    void testToString() {
        assertEquals("[D][✗] party (by: 12 Feb 2020 1:30 PM)",
                new Deadlines("party", "2020-02-12 1330").toString());
    }
}