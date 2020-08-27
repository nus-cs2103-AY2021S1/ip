import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoPrinting_doneTodo_success() {
        Task todo = new Todo("read");
        todo.markDone();
        assertEquals("[T][\u2713] read", todo.toString());
    }

    @Test
    public void testTodoPrinting_notDoneTodo_success() {
        Task todo = new Todo("read");
        assertEquals("[T][\u2718] read", todo.toString());
    }

    @Test
    public void testToTaskData_doneTodo_success() {
        Task todo = new Todo("read");
        todo.markDone();
        assertEquals("T ; 1 ; read", todo.toTaskData());
    }

    @Test
    public void testToTaskData_notDoneTodo_success() {
        Task todo = new Todo("read");
        assertEquals("T ; 0 ; read", todo.toTaskData());
    }
}
