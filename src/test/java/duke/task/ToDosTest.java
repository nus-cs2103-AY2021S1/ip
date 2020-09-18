package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ToDosTest {

    @Test
    void doneTask() {
        assertEquals("[T][\u2713] party",
                new ToDos("party").doneTask().toString());
    }

    @Test
    void testToString() {
        assertEquals("[T][\u274C] party",
                new ToDos("party").toString());
    }
}
