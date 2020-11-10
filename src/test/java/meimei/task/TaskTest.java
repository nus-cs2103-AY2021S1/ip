package meimei.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testGetTaskName() {
        assertEquals("Laundry", new Task("Laundry").getTaskName());
    }

    @Test
    public void isDone_isDoneAssignedTrue_true() {
        Task testTask = new Task("Housework");
        testTask.isDone = true;
        assertTrue(testTask.isDone());
    }

    @Test
    public void isDone_defaultTask_false() {
        Task testTask = new Task("Housework");
        assertFalse(testTask.isDone());
    }

    @Test
    public void markDone_taskMarkedDone_isDoneVariableIsTrue() {
        Task testTask = new Task("Housework");
        testTask.markDone();
        assertTrue(testTask.isDone);
    }

    @Test
    public void testStringConversionForDoneTask() {
        Task testTask = new Task("Dishes");
        testTask.markDone();
        assertEquals("[\u2713] Dishes", testTask.toString());
    }
}
