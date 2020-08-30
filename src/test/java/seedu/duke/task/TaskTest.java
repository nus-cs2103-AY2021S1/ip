package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void markTaskAsDone() {
        Task task = new ToDo("hi");
        assertDoesNotThrow(() -> task.markAsDone());
        String expected = "[T][\u2713] hi";
        assertEquals(expected, task.toString());
    }
}
