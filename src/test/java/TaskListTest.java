import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.storage.TaskList;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class TaskListTest {

    @Test
    public void doneTask_intTaskNumber_success() {
        List<Task> tmp = new ArrayList<>();
        ToDo task = new ToDo("testing");
        tmp.add(task);
        TaskList tasks = new TaskList(tmp);

        assertEquals(false, tasks.getTask(1).getIsDone());
        tasks.doneTask(1);
        assertEquals(true, tasks.getTask(1).getIsDone());
    }
}
