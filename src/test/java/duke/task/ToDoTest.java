package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    void testStringConversion() {
        assertEquals("[T][✗] return book", new ToDo("return book").toString());
        assertEquals("[T][✓] borrow book", new ToDo("borrow book", true).toString());
    }

    @Test
    void storeTest() {
        assertEquals("T F return book\n", new ToDo("return book").store());
        assertEquals("T T borrow book\n", new ToDo("borrow book", true).store());
    }
}
