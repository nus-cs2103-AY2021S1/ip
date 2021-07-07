package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private static final char CROSS = '\u2717';
    private static final char TICK = '\u2713';

    @Test
    void getContent() {
        Task task = new Task("test content", "todo");
        assertEquals(task.getContent(), "test content");
    }

    @Test
    void isCompleted() {
        Task task = new Task("test content", "todo");
        task.markAsDone();
        assertTrue(task.isCompleted());
    }

    @Test
    void getStatus() {
        Task task = new Task("test content", "todo");
        assertEquals(task.getType(), "todo");
    }

    @Test
    void markAsDone() {
        Task task = new Task("test content", "todo");
        task.markAsDone();
        assertTrue(task.isCompleted());
    }

    @Test
    void getTime() {
        Task task = new Task("test content", "deadline", "2020-08-23");
        assertEquals(task.getDate(), "Aug 23 2020");
    }

    @Test
    void testToString() {
        Task task = new Task("test content", "deadline", "2020-08-23");
        assertEquals(task.toString(), "[D][" + CROSS + "] test content (by: Aug 23 2020)");

        task.markAsDone();
        assertEquals(task.toString(), "[D][" + TICK + "] test content (by: Aug 23 2020)");
    }
}
