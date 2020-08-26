import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void testStringConversion() {
        assertEquals("[not done] read book", new Task("read book", false).toString());
    }

    @Test
    public void markAsComplete_incompleteTask_success() {
        Task testTask = new Task("read book", false);
        testTask.markAsComplete();
        assertTrue(testTask.getStatus());
    }
}
