package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetStatusIcon() {
        Task test = new ToDo("test");
        test.markAsDone();
        assertEquals("\u2713", test.getStatusIcon());
    }
}
