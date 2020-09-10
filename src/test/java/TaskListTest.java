import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Task;
import duke.TaskList;

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
