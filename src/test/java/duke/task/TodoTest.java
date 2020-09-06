package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private final Todo todo = new Todo("test");

    @Test
    void testIdentifier_CorrectOutput() {
        assertEquals("T", todo.getTaskIdentifier());
    }

    @Test
    void testToString_CorrectOutput() {
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    void testMarkAsDone_ChangesOutput() {
        todo.markAsDone();
        assertEquals("[T][X] test", todo.toString());
    }
}
