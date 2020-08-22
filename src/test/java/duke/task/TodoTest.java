package duke.task;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    private static Todo TODO_ONE = new Todo("test", false);
    private static Todo TODO_TWO = new Todo("test 2", true);

    @Test
    public void testCreateTodo() {
        assertEquals(Todo.createTodo("test"), TODO_ONE);
    }

    @Test
    public void testToString() {
        assertEquals(TODO_ONE.toString(), "[T][\u2717] test");
        assertEquals(TODO_TWO.toString(), "[T][\u2713] test 2");
    }

    @Test
    public void testGetTaskDatetime() {
        assertEquals(TODO_ONE.getTaskDatetime(), Optional.empty());
    }
}
