import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void printFormat_taskNotDone() {
        Todo testTodo = new Todo("test", Priority.HIGH);
        assertEquals(testTodo.toString(), "[T][\u2718] {HIGH} test");
    }

    @Test
    public void printFormat_taskDone() {
        Todo testTodo = new Todo("test", Priority.LOW, true);
        assertEquals(testTodo.toString(), "[T][\u2713] {LOW} test");
    }
}
