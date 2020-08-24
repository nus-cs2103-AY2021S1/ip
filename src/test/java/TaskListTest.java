import duke.task.Task;
import duke.task.ToDoTask;
import duke.utility.TaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void init() {
        taskList = new TaskList();
    }

    @Test
    public void TaskListIsEmptyTest() {
        Assertions.assertTrue(taskList.isEmpty());

        taskList.addTask(new ToDoTask("Sample task 1"));
        taskList.addTask(new ToDoTask("Sample task 2"));
        taskList.addTask(new ToDoTask("Sample task 3"));

        Assertions.assertFalse(taskList.isEmpty());
    }

    @Test
    public void TaskListAddTest() {
        taskList.addTask(new ToDoTask("Sample task 1"));
        taskList.addTask(new ToDoTask("Sample task 2"));
        taskList.addTask(new ToDoTask("Sample task 3"));

        Assertions.assertEquals(taskList.size(), 3);
    }

    @Test
    public void TaskListDeleteTest() {
        taskList.addTask(new ToDoTask("Sample task 1"));
        taskList.addTask(new ToDoTask("Sample task 2"));
        taskList.addTask(new ToDoTask("Sample task 3"));
        taskList.deleteTask(0);
        taskList.addTask(new ToDoTask("Sample task 4"));
        taskList.addTask(new ToDoTask("Sample task 5"));
        taskList.deleteTask(0);
        taskList.deleteTask(0);

        Assertions.assertEquals(taskList.size(), 2);
    }

    @Test
    public void TaskListGetTest() {
        Task task1 = new ToDoTask("Sample task 1");
        Task task2 = new ToDoTask("Sample task 2");
        Task task3 = new ToDoTask("Sample task 3");
        Task task4 = new ToDoTask("Sample task 4");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        taskList.addTask(task4);
        taskList.addTask(new ToDoTask("Sample task 5"));

        Assertions.assertTrue(task1 == taskList.getTask(0));
        Assertions.assertTrue(task3 == taskList.getTask(2));
    }
}
