package duke.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.ExceptionMessage;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.task.ToDo;

public class DukeTaskListTest {

    private final DukeTaskList taskList = new DukeTaskList();

    @Test
    public void constructorTest() {
        try {
            new DukeTaskList();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void getTask_validIndex_success() {
        Task taskToBeGet = ToDo.createToDo("read book");
        taskList.addTask(taskToBeGet);
        int validIndex = 1;

        try {
            Task taskGet = taskList.getTask(validIndex);
            assertEquals(taskToBeGet, taskGet);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void getTask_invalidIndex_exceptionThrown() {
        int invalidIndex = 10;

        Exception exception = assertThrows(
                InvalidIndexException.class, () -> taskList.getTask(invalidIndex));

        assertEquals(ExceptionMessage.getInvalidIndexMessage(invalidIndex + ""), exception.getMessage());
    }

    @Test
    public void addTask_normalInput_taskAdded() {
        Task taskToBeAdded = ToDo.createToDo("read book");
        int currentSize = taskList.getSize();

        try {
            taskList.addTask(taskToBeAdded);

            assertEquals(currentSize + 1, taskList.getSize());
            assertEquals(taskToBeAdded, taskList.getTask(currentSize + 1));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void deleteTask_validIndex_taskDeleted() {
        Task taskToBeDeleted = ToDo.createToDo("read book");
        taskList.addTask(taskToBeDeleted);
        int validIndex = taskList.getSize();
        int currentSize = taskList.getSize();

        try {
            Task deletedTask = taskList.deleteTask(validIndex);

            assertEquals(taskToBeDeleted, deletedTask);
            assertEquals(currentSize - 1, taskList.getSize());
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        int invalidIndex = -2;

        Exception exception = assertThrows(
                InvalidIndexException.class, () -> taskList.deleteTask(invalidIndex));

        assertEquals(ExceptionMessage.getInvalidIndexMessage(invalidIndex + ""), exception.getMessage());
    }

    @Test
    public void getSizeTest() {
        DukeTaskList newTaskList = new DukeTaskList();
        assertEquals(0, newTaskList.getSize());

        newTaskList.addTask(ToDo.createToDo("read book"));
        newTaskList.addTask(ToDo.createToDo("return book"));
        assertEquals(2, newTaskList.getSize());

        newTaskList.deleteTask(1);
        assertEquals(1, newTaskList.getSize());

        newTaskList.setTasks(new ArrayList<>());
        assertEquals(0, newTaskList.getSize());
    }

    @Test
    public void setTasksTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(ToDo.createToDo("read book"));
        tasks.add(ToDo.createToDo("return book"));

        try {
            taskList.setTasks(tasks);

            assertEquals(tasks, taskList.getTasks());
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void getTasksTest() {
        ArrayList<Task> tasksSet = new ArrayList<>();
        tasksSet.add(ToDo.createToDo("read book"));
        tasksSet.add(ToDo.createToDo("return book"));
        taskList.setTasks(tasksSet);

        try {
            ArrayList<Task> tasksGet = taskList.getTasks();

            assertEquals(tasksSet, tasksGet);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }
}
