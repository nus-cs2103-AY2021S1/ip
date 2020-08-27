package alice.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
