package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(task, manager.deleteTask(0));
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
