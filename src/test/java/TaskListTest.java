import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void deleteTask_zeroTaskNumber_dukeExceptionThrown() {
        List<Task> storedTasks = new ArrayList<>();
        TaskList taskList = new TaskList(storedTasks);
        Assertions.assertThrows(DukeException.class, () -> {
            taskList.markTaskAsDone(0);;
        });
    }
}
