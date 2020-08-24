import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void printFormat_taskNotDone() {
        Todo testTodo = new Todo("test");
        assertEquals(testTodo.toString(), "[T][\u2718] test");
    }

    @Test
    public void printFormat_taskDone() {
        Todo testTodo = new Todo("test", true);
        assertEquals(testTodo.toString(), "[T][\u2713] test");
    }
}
