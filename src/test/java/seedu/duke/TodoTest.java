package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;


public class TodoTest {
    @Test
    public void testGetDescription() {
        Todo todo = new Todo("abc");
        assertEquals(todo.getDescription(), "abc");
    }

    @Test
    public void testGetIsDone_default_false() {
        Todo todo = new Todo("abc");
        assertFalse(todo.getIsDone());
    }
}
