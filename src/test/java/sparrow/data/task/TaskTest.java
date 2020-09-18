package sparrow.data.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    private Task task = new Task("I am a task.");

    @Test
    public void equals() {
        Task sameTask = new Task("I am a task.");
        Task diffTask = new Task("I am another task.");

        // same description -> returns true
        assertTrue(task.equals(sameTask));

        // same description -> returns false
        assertFalse(task.equals(diffTask));

        // null -> returns false
        assertFalse(task.equals(null));

        // different type -> returns false
        assertFalse(task.equals(27));
    }
}
