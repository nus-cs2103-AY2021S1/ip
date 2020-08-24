package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void testStatusIcon() {
        assertEquals("\u2713", new Todo(true, "").getStatusIcon());
        assertEquals("\u2718", new Todo(false, "").getStatusIcon());
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Todo(false, "");
        task.markAsDone();
        assertEquals("\u2713", task.getStatusIcon());
    }
}
