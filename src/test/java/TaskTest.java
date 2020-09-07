import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testContainsTime() {
        Task task = new Todo("desc");
        assertEquals(true, task.containsTime("desc 2359"));
        assertEquals(true, task.containsTime("desc 0000"));
        assertEquals(false, task.containsTime("desc 0761"));
        assertEquals(false, task.containsTime("desc 2401"));
    }
}
