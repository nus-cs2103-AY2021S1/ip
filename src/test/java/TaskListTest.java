import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void doneTask_intTaskNumber_success() {
        List<Task> tmp = new ArrayList<>();
        ToDo task = new ToDo("testing");
        tmp.add(task);
        TaskList tasks = new TaskList(tmp);

        assertEquals(false, tasks.getTask(1).isDone);
        tasks.doneTask(1);
        assertEquals(true, tasks.getTask(1).isDone);
    }
}
