package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeadlinesTest {

    @Test
    void returnTime() {
        assertEquals("2020-02-12 1330",
                new Deadlines("party", "2020-02-12 1330").returnTime());
    }

    @Test
    void doneTask() {
        assertEquals("[D][\u2713] party (by: 12 Feb 2020 1:30 PM)",
                new Deadlines("party", "2020-02-12 1330").doneTask().toString());
    }

    @Test
    void testToString() {
        assertEquals("[D][\u274C] party (by: 12 Feb 2020 1:30 PM)",
                new Deadlines("party", "2020-02-12 1330").toString());
    }
}
