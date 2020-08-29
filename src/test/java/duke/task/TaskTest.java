package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private final Task incompleteTask = new Task("incomplete task");
    private final Task completedTask = new Task("completed task");

    @Test
    public void testGetTaskName() {
        assertEquals("incomplete task", incompleteTask.getTaskName());
        assertEquals("completed task", completedTask.getTaskName());
    }

    @Test
    public void testGetStatusIcon() {
        completedTask.markAsDone();
        assertEquals("\u2718", incompleteTask.getStatusIcon());
        assertEquals("\u2713", completedTask.getStatusIcon());
    }

    @Test
    public void testMarkAsDone() {
        completedTask.markAsDone();
        assertTrue(completedTask.isDone);
        assertEquals("1", completedTask.completed);
    }

    @Test
    public void testTaskToText() {
        assertEquals("", incompleteTask.taskToText());
        assertEquals("", completedTask.taskToText());
    }
}
