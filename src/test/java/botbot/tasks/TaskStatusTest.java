package botbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskStatusTest {
    @Test
    public void convertToStatus_success() {
        assertEquals(TaskStatus.DONE, TaskStatus.convertToStatus("1"));
        assertEquals(TaskStatus.NOT_DONE, TaskStatus.convertToStatus("0"));
    }

    @Test
    public void convertToStatus_assertion() {
        try {
            assertEquals(TaskStatus.DONE, TaskStatus.convertToStatus("2"));
            fail();
        } catch (AssertionError e) {
            assertEquals("Invalid task status", e.getMessage());
        }
    }

    @Test
    public void getStrValue() {
        assertEquals("1", TaskStatus.DONE.getStrValue());
        assertEquals("0", TaskStatus.NOT_DONE.getStrValue());
    }

    @Test
    public void getStatusIcon() {
        assertEquals("\u2713", TaskStatus.DONE.getStatusIcon());
        assertEquals("\u2717", TaskStatus.NOT_DONE.getStatusIcon());
    }
}
