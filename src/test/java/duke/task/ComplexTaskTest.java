package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ComplexTaskTest {

    private final String description = "workout";
    private final String time = "2pm";

    /**
     * Tests dead
     */
    @Test
    public void testMethods() {
        ComplexTask newDeadline = new ComplexTask(description, TaskType.DEADLINE, time);
        // Check default
        assertFalse(newDeadline.isDone());
        assertEquals("Not done", newDeadline.getStatus());
        assertEquals(time, newDeadline.getTime());
        // Check done
        newDeadline.markAsDone();
        assertTrue(newDeadline.isDone());
        assertEquals("Done", newDeadline.getStatus());
        assertEquals(String.format("[D][\u2713] %s (by: %s)", description, time), newDeadline.toString());
    }

    /**
     * Tests creation of deadline task.
     */
    @Test
    public void testDeadline() {
        ComplexTask newDeadline = new ComplexTask(description, TaskType.DEADLINE, time);
        assertEquals("DEADLINE", newDeadline.getTaskName());
        assertEquals(String.format("[D][\u2718] %s (by: %s)", description, time), newDeadline.toString());
    }

    /**
     * Tests creation of event task.
     */
    @Test
    public void testEvent() {
        ComplexTask newEvent = new ComplexTask(description, TaskType.EVENT, time);
        assertEquals("EVENT", newEvent.getTaskName());
        assertEquals(String.format("[E][\u2718] %s (at: %s)", description, time), newEvent.toString());
    }

    /**
     * Tests the second constructor with 4 parameters in ComplexTask.
     */
    @Test
    public void testSecondConstructor() {
        ComplexTask newEvent = new ComplexTask(description, true, TaskType.EVENT, time);
        assertTrue(newEvent.isDone());
        newEvent = new ComplexTask(description, false, TaskType.EVENT, time);
        assertFalse(newEvent.isDone());
    }
}
