package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void testStringConversion() {
        assertEquals("[\u2717] return book", new Task("return book").toString());
        assertEquals("[\u2713] borrow book", new Task("borrow book", true).toString());
    }

    @Test
    void store() {
        assertEquals("F return book", new Task("return book").store());
        assertEquals("T borrow book", new Task("borrow book", true).store());
    }

    @Test
    void markAsDone() {
        Task task = new Task("homework");
        task.markAsDone();
        assertEquals("[\u2713] homework", task.toString());
    }
}
