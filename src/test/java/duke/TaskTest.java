import duke.DukeException;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTaskMarkDone_success() throws DukeException {
        Task task = new Task("description");
        task.markDone();
        assertEquals(task.getIsDone(), true);
    }

}
