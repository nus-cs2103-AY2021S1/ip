import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.task.Task;

public class TaskTest {
    @Test
    public void testTaskMarkDone_success() throws DukeException {
        Task task = new Task("description");
        task.markDone();
        assertEquals(task.getIsDone(), true);
    }

    @Test
    public void testCreateCompletedTask_success() {
        Task task = new Task("do homework", true);
        assertEquals("[\u2713] do homework", task.toString());
    }

    @Test
    public void testCreateTask_success() {
        Task task = new Task("do homework");
        assertEquals("[\u2718] do homework", task.toString());
    }
}
