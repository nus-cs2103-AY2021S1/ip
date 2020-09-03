package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    private final String description = "workout";

    @Test
    public void testWithDefault() {
        ToDo newToDo = new ToDo(description);

        // Check default
        assertFalse(newToDo.isDone());
        assertEquals("Not done", newToDo.getStatus());
        assertEquals("TODO", newToDo.getTaskName());
        assertEquals("-", newToDo.getTime());
        assertEquals(description, newToDo.getDescription());
        assertEquals(String.format("[T][\u2718] %s", description), newToDo.toString());

        // Mark as done
        newToDo.markAsDone();
        assertTrue(newToDo.isDone());
        assertEquals("Done", newToDo.getStatus());
        assertEquals(String.format("[T][\u2713] %s", description), newToDo.toString());
    }
    @Test
    public void testConstructor() {
        ToDo newTodo = new ToDo(description, false);
        // Check default
        assertFalse(newTodo.isDone());
        newTodo = new ToDo(description, true);
        // Check default
        assertTrue(newTodo.isDone());
    }

    @Test
    public void testMatch() {
        String description1 = "man";
        String description2 = "Man";
        String description3 = "woman";
        String description4 = "mane";
        String toMatch = "man";
        assertTrue(new ToDo(description1).match(toMatch));
        assertFalse(new ToDo(description2).match(toMatch));
        assertTrue(new ToDo(description3).match(toMatch));
        assertTrue(new ToDo(description4).match(toMatch));
    }
}
