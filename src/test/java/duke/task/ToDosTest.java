package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDosTest {

    @Test
    void doneTask() {
        assertEquals("[T][✓] party",
                new ToDos("party").doneTask().toString());
    }

    @Test
    void testToString() {
        assertEquals("[T][✗] party",
                new ToDos("party").toString());
    }
}