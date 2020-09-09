package duke.task;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task task = new Task("Run 10 km", false);

    @Test
    public void getIsDone() {
        assertEquals(false, task.getIsDone());
    }

    @Test
    public void getDescription() {
        assertEquals("Run 10 km", task.getDescription());
    }

    @Test
    public void taskToString() {
        assertEquals(String.format("[%s] %s", "\u2718", "Run 10 km"), task.toString());
    }
}