import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.ToDoTask;
import duke.utility.TaskList;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void init() {
        taskList = new TaskList();
    }

    @Test
    public void taskListIsEmptyTest() {
        Assertions.assertTrue(taskList.isEmpty());
        taskList.addTask(new ToDoTask("Sample task"));
        Assertions.assertFalse(taskList.isEmpty());
    }

    @Test
    public void taskListAddTest() {
        taskList.addTask(new ToDoTask("Sample task"));
        Assertions.assertEquals(taskList.size(), 1);
    }

    @Test
    public void taskListDeleteTest() {
        taskList.addTask(new ToDoTask("Sample task 1"));
        taskList.addTask(new ToDoTask("Sample task 2"));
        taskList.deleteTask(0);

        Assertions.assertEquals(taskList.size(), 1);
    }

    @Test
    public void taskListGetTest() {
        Task task1 = new ToDoTask("Sample task 1");
        Task task2 = new ToDoTask("Sample task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        Assertions.assertTrue(task1 == taskList.getTask(0));
        Assertions.assertTrue(task2 == taskList.getTask(1));
    }
}
