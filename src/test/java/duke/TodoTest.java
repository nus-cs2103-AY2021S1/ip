package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    void testToString() {
        Todo todo = new Todo("todo");
        System.out.println(todo);
        assertEquals(todo.toString(), "[T][✗] todo");
    }
}