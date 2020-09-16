import duke.task.Task;
import duke.admin.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TaskListTest {
    @Test

    public void getArrayListTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("1"));
        tasks.add(new Todo("2"));
        TaskList tmp = new TaskList(tasks);
        assert(tmp.getArrayList().equals(tasks));
    }
}
