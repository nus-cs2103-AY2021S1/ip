package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString_doneDeadline() {
        assertEquals("[D][\u2713] Submit homework (by: Aug 29 2020 1200)",
                new Deadline("Submit homework", "Aug 29 2020 1200", true).toString());
    }

    @Test
    void testToString_undoneDeadline() {
        assertEquals("[D][\u2718] Submit essay (by: Aug 29 2020 2359)",
                new Deadline("Submit essay", "Aug 29 2020 2359", false).toString());
    }
}