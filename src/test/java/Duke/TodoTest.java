package Duke;

import Task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests if the Todo_task is correctly implemented.
 * @author Joshua
 */
public class TodoTest {

    Todo unfinishedTodo = new Todo("return book");
    Todo doneTodo = new Todo("return 2 books");

    /**
     * Tests if the Todo_task is saved and loaded in the correct format.
     */
    @Test
    public void testSaveFormat() {
        doneTodo.completeTask();
        assertEquals("[T] [✗]return book", unfinishedTodo.saveFormat());
        assertEquals("[T] [✓]return 2 books", doneTodo.saveFormat());
    }

    /**
     * Tests if the display format of the Todo_task is correctly implemented.
     */
    @Test
    public void testToString() {
        doneTodo.completeTask();
        assertEquals("[T] [✗]return book", unfinishedTodo.toString());
        assertEquals("[T] [✓]return 2 books", doneTodo.toString());
    }
}