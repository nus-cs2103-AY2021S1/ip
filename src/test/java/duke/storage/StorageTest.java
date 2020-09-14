package duke.storage;

import static duke.util.Keyword.DATE_TIME_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.FileUpdateFailException;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

/**
 * Tests interaction with user storage.
 */
public class StorageTest {

    private final Storage storage = new Storage();

    /**
     * Tests updating of tasks in storage.
     */
    @Test
    public void testTaskUpdate() {
        try {
            TaskList taskList = new TaskList(new ArrayList<>());
            Task task1 = new ToDo("cs2103t");
            Task task2 = new ToDo("tutorial");
            Task task3 = new ToDo("test");
            taskList.add(task1);
            taskList.add(task2);
            taskList.add(task3);
            // Update
            storage.updateFile(taskList);
            // After updating
            ArrayList<Task> tasks = storage.getTasks();
            // Tests
            assertEquals(3, tasks.size());
            assertEquals(task1.getDescription(), tasks.get(0).getDescription());
            assertEquals(task2.getDescription(), tasks.get(1).getDescription());
            assertEquals(task3.getDescription(), tasks.get(2).getDescription());
            for (Task task : tasks) {
                assertEquals("TODO", task.getTaskName());
                String taskCreationTime = task.getTime();
                LocalDateTime.parse(taskCreationTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
            }
        } catch (FileUpdateFailException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    /**
     * Tests updating of empty tasks in storage.
     */
    @Test
    public void testEmptyTasks() {
        TaskList emptyTaskList = new TaskList(new ArrayList<>());
        try {
            storage.updateFile(emptyTaskList);
            ArrayList<Task> emptyTasks = storage.getTasks();
            assertTrue(emptyTasks.isEmpty());
        } catch (FileUpdateFailException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
