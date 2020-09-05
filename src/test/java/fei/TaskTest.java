package fei;

import fei.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void markAsDoneTest() {
        Task task = new Task("sample");
        task.markAsDone();
        assertEquals("✓", task.getStatusIcon());
    }
}
