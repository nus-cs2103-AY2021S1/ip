package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.List;


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
