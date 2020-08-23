package duke;

import org.junit.jupiter.api.Test;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void createTask_createUndoneDeadlineTask_deadlineTaskReturned() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task createdTask = taskList.createTask(TaskType.TypeOfTask.DEADLINE, "Test",
                LocalDateTime.parse("2020-08-23T00:00"), false);

        assertEquals("[D][\u2717] Test (by: Aug 23 2020, 00:00)", createdTask.toString());
    }

    @Test
    public void listTask_listAllTasks_allTasksReturnedAsString() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("Test1", false));
        taskList.addTask(new Todo("Test2", true));
        assertEquals("1. [T][\u2717] Test1\n2. [T][\u2713] Test2", taskList.listTasks());

    }
}
