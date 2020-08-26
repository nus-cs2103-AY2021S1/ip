package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testDescription() {
        assertEquals("read book", new Task("read book").getDescription());
    }

    @Test
    public void testStatusIcon() {
        Task task = new Task("read book");
        task.markAsDone();
        assertEquals("\u2713", task.getStatusIcon());
    }

    @Test
    public void testToString() {
        assertEquals("[\u2718] read book", new Task("read book").toString());
    }
}
