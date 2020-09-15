package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class that encapsulates tests for the Task class.
 */
public class TaskTest {
    /**
     * Tests the toString() method for Task.
     */
    @Test
    public void toString_normal_formattedString() {
        Task t = new Task("test");
        assertEquals("[✘] test", t.toString());
    }
}
