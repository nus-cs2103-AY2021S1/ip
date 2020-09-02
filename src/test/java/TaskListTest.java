import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.addTask(new Todo("Test", false));
        assertEquals(tasks.toString(), "1.[T][✗] Test");
    }
}
