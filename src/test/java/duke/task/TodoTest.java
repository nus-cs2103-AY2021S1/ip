package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private final Todo todo1 = new Todo("example 1", false);
    private final Todo todo2 = new Todo("example 2", true);

    @Test
    public void testTodoToString() {
        assertEquals("[T][✗] example 1", todo1.toString());
        assertEquals("[T][✓] example 2", todo2.toString());
    }

    @Test
    public void testTodoToSaveString() {
        assertEquals("T|0|example 1", todo1.toSaveString());
        assertEquals("T|1|example 2", todo2.toSaveString());
    }
}
