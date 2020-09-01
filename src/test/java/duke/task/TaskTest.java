package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.stub.task.TaskStub;

public class TaskTest {
    private final Task task = new TaskStub("This is a test");

    @BeforeEach
    public void markAsUndone() {
        task.isDone = false;
    }

    @Test
    public void getStatusIcon() {
        assertEquals("\u2718", task.getStatusIcon());

        task.markAsDone();

        assertEquals("\u2713", task.getStatusIcon());
    }

    @Test
    public void containsKeyword() {
        assertTrue(task.containsKeyword("test"));
        assertTrue(task.containsKeyword("ThIs Is"));
        assertFalse(task.containsKeyword("test."));
    }

    @Test
    public void toStringTest() {
        String expected1 = "[\u2718] This is a test";
        assertEquals(expected1, task.toString());

        task.markAsDone();

        String expected2 = "[\u2713] This is a test";
        assertEquals(expected2, task.toString());
    }
}
