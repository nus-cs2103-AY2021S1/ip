package duke;

import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTask_newTask_success() throws DukeException {
        Task expectedTask = new Todo("Todo");
        TaskList tasks = new TaskList();

        tasks.addTask(TaskType.TODO, "Todo", null);
        assertEquals(expectedTask.toSaveData(), tasks.getTask(1).toSaveData());
    }

    @Test
    public void deleteTask_taskNo_success() throws DukeException {
        List<Task> tasks = Arrays.asList(new Todo("Todo"));
        TaskList taskList = new TaskList(tasks);
        int initialSize = taskList.getTasks().size();

        taskList.deleteTask(1);
        assertEquals(initialSize - 1, taskList.getTasks().size());
    }
}