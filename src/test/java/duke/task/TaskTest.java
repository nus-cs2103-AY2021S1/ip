package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void createTask_withDescription_taskCreated() {
        Task test = new Task("description");
        assertTrue(test instanceof Task);
    }

    @Test
    public void createTask_withoutDescription_taskCreated() {
        Task test = new Task("");
        assertTrue(test instanceof Task);
    }

    @Test
    public void getTaskStatus_taskDone_oneString() {
        Task test = new Task("");
        test.markAsDone();
        assertEquals("1", test.getStatusIcon());
    }

    @Test
    public void getTaskStatus_taskNotDone_zeroString() {
        Task test = new Task("");
        assertEquals("0", test.getStatusIcon());
    }
}
