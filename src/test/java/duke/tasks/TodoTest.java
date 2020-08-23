package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToFileString() {
        String description = "description";
        Todo todo = new Todo(description);
        String expected = "T | 0 | " + description;
        assertEquals(expected, todo.toFileString());
    }

    @Test
    public void testToString() {
        String description = "description";
        Todo todo = new Todo(description);
        String expected = "[T][\u2718] " + description;
        assertEquals(expected, todo.toString());
    }

    @Test
    public void toFileString_markedAsDone() {
        String description = "description";
        Todo todo = new Todo(description);
        todo.markAsDone();
        String expected = "T | 1 | " + description;
        assertEquals(expected, todo.toFileString());
    }

    @Test
    public void toString_markedAsDone() {
        String description = "description";
        Todo todo = new Todo(description);
        todo.markAsDone();
        String expected = "[T][\u2713] " + description;
        assertEquals(expected, todo.toString());
    }
}
