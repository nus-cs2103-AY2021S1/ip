import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class that handles testing of Todo class toString() method
 *
 */
class TodoTest {

    @Test
    void testToString() {
        Todo todo = new Todo("enjoy life");
        assertEquals("[T][✘] enjoy life", todo.toString());
    }
}