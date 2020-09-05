package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    void testStringConversion() {
        assertEquals("[T][\u2717] return book", new ToDo("return book").toString());
        assertEquals("[T][\u2713] borrow book", new ToDo("borrow book", true).toString());
    }

    @Test
    void storeTest() {
        assertEquals("T F return book", new ToDo("return book").store());
        assertEquals("T T borrow book", new ToDo("borrow book", true).store());
    }
}
