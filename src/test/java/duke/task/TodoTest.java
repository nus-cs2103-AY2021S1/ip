package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TodoTest {
    private final Todo todo = new Todo("test");

    @Test
    void testCorrectIdentifier() {
        assertEquals("T", todo.getTaskIdentifier());
    }

    @Test
    void testToString() {
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    void testMarkAsDone() {
        todo.markAsDone();
        assertEquals("[T][X] test", todo.toString());
    }
}
