package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {
    @Test
    void testToString() {
        Todo todo = new Todo("todo");
        assertEquals(todo.toString(), "[T][✗] todo");
    }
}
