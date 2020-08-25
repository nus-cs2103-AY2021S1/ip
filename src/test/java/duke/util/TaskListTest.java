package duke.util;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void add_ToDo_tasksSizeIsOne() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("hello world"));
        assertEquals(1, tasks.size());
    }

    @Test
    public void get_taskIndex_taskReturned() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("hello world :)");
        tasks.add(task);
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void get_outOfBoundsIndex_errorThrown() {
        try {
            TaskList tasks = new TaskList();
            Task task = new ToDo("hello world :)");
            tasks.add(task);
            assertEquals(task, tasks.get(1));
            fail("No exception thrown");
        } catch (IndexOutOfBoundsException e) {
            String expected = "Index 1 out of bounds for length 1";
            assertEquals(expected, e.getMessage());
        }
    }
}
