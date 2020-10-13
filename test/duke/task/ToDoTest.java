package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ToDoTest {

    @Test
    void testToString() {
        assertEquals("[T][✘] do", new ToDo("do").toString());
    }

    @Test
    void toFileString() {
        assertEquals("T\nF\ndo\n", new ToDo("do").toFileString());
    }
}
