package duke;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tool.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void getNumOfTasksTest1() {
        TaskList tl = new TaskList(new ArrayList<Task>());
        assertEquals(tl.getNumOfTasks(), 0);
    }

    @Test
    public void getNumOfTasksTest2() {
        TaskList tl = new TaskList(new ArrayList<Task>());
        tl.add(new Todo("eat lunch", false));
        tl.add(new Todo("have fun", true));
        assertEquals(tl.getNumOfTasks(), 2);
    }

    @Test
    public void getNumOfTasksTest3() {
        TaskList tl = new TaskList(new ArrayList<Task>());
        tl.add(new Todo("eat lunch", false));
        tl.add(new Todo("have fun", true));
        tl.delete(1);
        assertEquals(tl.getNumOfTasks(), 1);
    }
}
