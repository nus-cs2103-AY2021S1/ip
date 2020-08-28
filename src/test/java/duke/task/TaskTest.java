package duke.task;

import duke.stub.task.TaskStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    private final Task TASK = new TaskStub("This is a test");

    @BeforeEach
    public void markAsUndone() {
        TASK.isDone = false;
    }

    @Test
    public void getStatusIcon() {
        assertEquals("\u2718", TASK.getStatusIcon());

        TASK.markAsDone();

        assertEquals("\u2713", TASK.getStatusIcon());
    }

    @Test
    public void containsKeyword() {
        assertTrue(TASK.containsKeyword("test"));
        assertTrue(TASK.containsKeyword("ThIs Is"));
        assertFalse(TASK.containsKeyword("test."));
    }

    @Test
    public void toStringTest() {
        String expected1 = "[\u2718] This is a test";
        assertEquals(expected1, TASK.toString());

        TASK.markAsDone();

        String expected2 = "[\u2713] This is a test";
        assertEquals(expected2, TASK.toString());
    }
}
