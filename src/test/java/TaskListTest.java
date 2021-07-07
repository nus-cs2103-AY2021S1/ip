import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.addTask(new Todo("Test", false));
        assertEquals(tasks.toString(), "1.[T][\u2718] Test");
    }
}
