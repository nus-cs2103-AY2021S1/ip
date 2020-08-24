import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
