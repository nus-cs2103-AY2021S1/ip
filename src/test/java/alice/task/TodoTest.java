package alice.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void testStringConversion() {
        Task task = new Todo("Test");
        // test toString
        assertEquals(task.toString(), "[T][X] Test");

        // test encode
        assertEquals(task.encode(), "T | 0 | Test");
    }
}
