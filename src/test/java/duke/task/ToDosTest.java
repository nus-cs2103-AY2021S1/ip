package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
