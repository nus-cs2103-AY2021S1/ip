import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void getTaskTypeTest() {
        Task task = new Todo("borrow book");
        String result = task.getTaskType();
        assertEquals("T", result);
    }

    @Test
    public void getStatusIconTest() {
        Task task = new Todo("borrow book");
        task.markAsDone();
        assertEquals("\u2713", task.getStatusIcon());
    }
}
