import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodo() {
        Todo testTodo = new Todo("Todo 1");
        assertEquals("[T][✗] Todo 1",testTodo.toString());
    }
    @Test
    public void testTodoDone() {
        Todo testTodo = new Todo("Todo 1");
        testTodo.setDone();
        assertEquals("[T][✓] Todo 1",testTodo.toString());
    }
}
