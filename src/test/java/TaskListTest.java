import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TaskListTest {
    @Test
    public void testDeleteTask() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.addTask(new Task("shopping"));
        tasks.addTask(new Task("study"));
        tasks.deleteTask(0);
        assertEquals(1, tasks.getSize());
    }
}
