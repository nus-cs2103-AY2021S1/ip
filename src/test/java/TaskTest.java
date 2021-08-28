import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Task;

public class TaskTest {
    Task task = new Task("Test Task");

    @Test
    public void testToString() {
        assertEquals("[Not Done] Test Task", task.toString());
    }

    @Test
    public void testToStringFileFormat() {
        assertEquals("[Not Done] Test Task", task.toStringFileFormat());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals("Not Done", task.getStatusIcon());
        task.setDone();
        assertEquals("Done", task.getStatusIcon());
    }
}
