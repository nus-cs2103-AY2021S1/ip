import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void string() {
        Task task = new Task("test");
        assertEquals(task.toString(), "[?][\u2718] test");
        task.markDone();
        assertEquals(task.toString(), "[?][\u2713] test");
    }
}