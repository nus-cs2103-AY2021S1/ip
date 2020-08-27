package alison;

import alison.exception.AlisonException;
import alison.task.Deadline;
import alison.task.Task;
import alison.tool.Parser;
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
