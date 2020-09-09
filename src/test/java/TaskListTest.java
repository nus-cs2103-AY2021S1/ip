import main.java.Event;
import main.java.TaskList;
import main.java.Task;
import main.java.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void sizeTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read"));
        tasks.add(new Event("sleep", "noon"));
        TaskList taskList = new TaskList(tasks);
        assertEquals(2, taskList.size());
    }

    @Test
    public void getTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read"));
        Event event = new Event("sleep", "noon");
        tasks.add(event);
        TaskList taskList = new TaskList(tasks);
        assertEquals(event, taskList.get(1));
    }
}
