import org.junit.jupiter.api.Test;
import juke.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testSavedString() {
        assertEquals("T/0/read book", new Todo("read book").taskSaver());
    }
}