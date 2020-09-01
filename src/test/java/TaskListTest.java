import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import data.Task;
import data.TaskList;
import data.ToDo;



/**
 * Test the TaskList.
 */

public class TaskListTest {
    private TaskList tasks = new TaskList(new ArrayList<Task>());

    @Test
    public void getTask_tasksEmpty_nullResult() {
        assertEquals(tasks.get(0), null);
    }

    @Test
    public void addAndGetTask_taskAdded() {
        Task task = new ToDo("Sleep");
        tasks.add(task);
        assertEquals(tasks.get(0), task);
    }



}
