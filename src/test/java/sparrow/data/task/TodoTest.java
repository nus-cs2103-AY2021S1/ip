package sparrow.data.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {

    Todo todo = new Todo("I am a todo.");

    @Test
    public void equals() {
        Todo sameTodo = new Todo("I am a todo.");
        Todo diffTodo = new Todo("I am another todo.");

        // same description -> returns true
        assertTrue(todo.equals(sameTodo));

        // same description -> returns false
        assertFalse(todo.equals(diffTodo));

        // null -> returns false
        assertFalse(todo.equals(null));

        // different type -> returns false
        assertFalse(todo.equals(27));
    }
}
