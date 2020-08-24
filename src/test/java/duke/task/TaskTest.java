package duke.task;

import main.java.duke.task.Task;
import main.java.duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void markTaskAsDone() {
        Task task = new ToDo("hi");
        assertDoesNotThrow(() -> task.markAsDone());
        String expected = "[T][✓] hi";
        assertEquals(expected, task.toString());
    }
}
