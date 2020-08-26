import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString() {
        Todo todo = new Todo("enjoy life");
        assertEquals("[T][✘] enjoy life", todo.toString());
    }
}