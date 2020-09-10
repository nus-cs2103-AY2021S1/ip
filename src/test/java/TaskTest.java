import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void getStatusIcon() {
        Task task = new Task(true, "read book");
        assertEquals("✓", task.getStatusIcon());
    }

    @Test
    void markAsDone() {
        Task task = new Task("read book");
        task.markAsDone();
        assertEquals("✓", task.getStatusIcon());
    }

    @Test
    void testToString() {
        Task task = new Task("read book");
        assertEquals("[✗] read book", task.toString());
    }
}
