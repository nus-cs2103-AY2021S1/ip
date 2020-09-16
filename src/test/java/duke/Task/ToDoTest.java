package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    void stringConversion() {
        ToDo todo = new ToDo("Test");
        assertEquals("[T][✘][4] Test", todo.toString());
    }

    @Test
    void serializeTest() {
        ToDo todo = new ToDo("Test");
        assertEquals("T | 0 | 4 | Test", todo.serialize());
    }
}
