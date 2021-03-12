package alice.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void testStringConversion() {
        Task task = new Task(false, "TaskTest") {
        };
        // test toString
        assertEquals(task.toString(), "[✘] TaskTest");

        // test encode
        assertEquals(task.encode(), "0 | TaskTest");
    }

    @Test
    void testMarkAsDone() {
        Task task = new Task(false, "TaskTest") {
        };
        task.markAsDone();
        // test toString
        assertEquals(task.toString(), "[✔] TaskTest");

        // test encode
        assertEquals(task.encode(), "1 | TaskTest");
    }

    @Test
    void testContainKeywords() {
        Task task = new Task(false, "Key1 Key2 Key3") {
        };
        // Search 1 keyword
        assertTrue(task.containKeywords("Key1"));

        // Search by multiple keywords
        assertTrue(task.containKeywords("InvalidKey", "Key3"));

        // Keyword not found
        assertFalse(task.containKeywords("InvalidKey"));
    }
}
