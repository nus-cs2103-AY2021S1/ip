package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void test() {
        String description = "workout";
        ToDo newToDo = new ToDo(description);
        // Check default
        assertFalse(newToDo.isDone());
        assertEquals("Not done", newToDo.getStatus());
        assertEquals("TODO", newToDo.getTaskName());
        assertEquals("-", newToDo.getTime());
        assertEquals(String.format("[T][\u2718] %s", description), newToDo.toString());

        newToDo.markAsDone();
        assertTrue(newToDo.isDone());
        assertEquals("Done", newToDo.getStatus());
        assertEquals(String.format("[T][\u2713] %s", description), newToDo.toString());
    }
}
