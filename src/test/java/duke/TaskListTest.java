package duke;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    
    @Test
    public void totalTasksCheck() {
        TaskList list = new TaskList(new ArrayList<Task>());
        list.addTask(new Todo("sleep", false));
        list.addTask(new Todo("eat",true));
        assertEquals(list.getTotalTask(),2);
    }
    
    @Test
    public void deleteTaskIndexTest() {
        TaskList list = new TaskList(new ArrayList<Task>());
        list.addTask(new Todo("sleep", false));
        list.addTask(new Todo("eat",true));
        list.deleteTaskIndex(0);
        assertEquals(list.getTotalTask(),1);
    }
}
