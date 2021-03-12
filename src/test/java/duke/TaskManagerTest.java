package duke;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskManager;

public class TaskManagerTest {
    @Test
    public void testAddTask() {
        Task task = new Task("read book");
        TaskManager manager = new TaskManager();
        manager.addTask(task);
        assertEquals(task, manager.getTasks().get(0));
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task("buy bread");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);
        TaskManager manager = new TaskManager(tasks);
        manager.deleteTask(task);
        assertEquals(0, manager.getTasks().size());
    }

    @Test
    public void testMarkTaskAsDone() {
        Task task = new Task("buy bread");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);
        TaskManager manager = new TaskManager(tasks);
        assertFalse(manager.getTasks().get(0).isDone());
        manager.markTaskAsDone(task);
        assertTrue(manager.getTasks().get(0).isDone());
    }
}
