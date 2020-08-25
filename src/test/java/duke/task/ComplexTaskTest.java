package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexTaskTest {

    @Test
    public void test() {
        String description = "workout";
        String time = "2pm";
        ComplexTask newDeadline = new ComplexTask(description, time, TaskType.DEADLINE);
        // Check default
        assertFalse(newDeadline.isDone());
        assertEquals("Not done", newDeadline.getStatus());
        assertEquals("DEADLINE", newDeadline.getTaskName());
        assertEquals(time, newDeadline.getTime());
        assertEquals(String.format("[D][\u2718] %s (by: %s)", description, time), newDeadline.toString());

        newDeadline.markAsDone();
        assertTrue(newDeadline.isDone());
        assertEquals("Done", newDeadline.getStatus());
        assertEquals(String.format("[D][\u2713] %s (by: %s)", description, time), newDeadline.toString());
    }

}
