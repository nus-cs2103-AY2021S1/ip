package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void markAsDone_uncompletedTask_completedTask() {
        String description = "testDescription";
        Task task = new Task(description);
        task.markAsDone();
        assertTrue(task.getIsDone());
    }

    @Test
    public void getStatusIcon_uncompletedTask_crossSymbol() {
        String description = "test";
        Task task = new Task(description);
        assertEquals("\u2718", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_completedTask_tickSymbol() {
        String description = "test";
        Task task = new Task(description);
        task.markAsDone();
        assertEquals("\u2713", task.getStatusIcon());
    }

    @Test
    public void testStringConversion() {
        String description = "test";
        Task task = new Task(description);
        assertEquals("|[\u2718] | test", task.toString());
    }
}
