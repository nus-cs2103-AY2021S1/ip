import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    ToDo todo = new ToDo("read books");
    
    @Test
    public void testToString() {
        assertEquals("[T][✘] read books", todo.toString());
    }

    @Test
    public void testGetData() {
        assertEquals("t/0/read books", todo.getData());
    }
}
