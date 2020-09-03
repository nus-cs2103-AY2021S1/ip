package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ComplexTaskTest {

    private final String description = "workout";
    private final String time = "2pm";

    @Test
    public void testDeadlineAndMethods() {
        ComplexTask newDeadline = new ComplexTask(description, TaskType.DEADLINE, time);
        // Check default
        assertEquals("DEADLINE", newDeadline.getTaskName());
        assertFalse(newDeadline.isDone());
        assertEquals("Not done", newDeadline.getStatus());
        assertEquals(time, newDeadline.getTime());
        assertEquals(String.format("[D][\u2718] %s (by: %s)", description, time), newDeadline.toString());

        newDeadline.markAsDone();
        assertTrue(newDeadline.isDone());
        assertEquals("Done", newDeadline.getStatus());
        assertEquals(String.format("[D][\u2713] %s (by: %s)", description, time), newDeadline.toString());
    }

    @Test
    public void testEvent() {
        ComplexTask newEvent = new ComplexTask(description, TaskType.EVENT, time);
        assertEquals("EVENT", newEvent.getTaskName());
        assertEquals(String.format("[E][\u2718] %s (at: %s)", description, time), newEvent.toString());
    }
    @Test
    public void testSecondConstructor() {
        ComplexTask newEvent = new ComplexTask(description, true, TaskType.EVENT, time);
        assertTrue(newEvent.isDone());
        newEvent = new ComplexTask(description, false, TaskType.EVENT, time);
        assertFalse(newEvent.isDone());
    }
}
