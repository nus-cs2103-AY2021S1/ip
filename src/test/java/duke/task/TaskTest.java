package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testGetStatusIcon() {
        Task test = new ToDo("test");
        test.markAsDone();
        assertEquals("\u2713", test.getStatusIcon());
    }
}
