import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    public void testCreateTodo() {
        assertEquals("[T]✘read book", new Todo("read book").toString());
    }
}