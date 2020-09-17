import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void getDescription() {
        Todo todo = new Todo("read book", false);
        assertEquals("read book", todo.getDescription());
    }
}