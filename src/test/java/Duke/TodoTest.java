package Duke;

import Task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    Todo unfinishedTodo = new Todo("return book");
    Todo doneTodo = new Todo("return 2 books");

    @Test
    public void testSaveFormat() {
        doneTodo.completeTask();
        assertEquals("[T] [✗]return book", unfinishedTodo.saveFormat());
        assertEquals("[T] [✓]return 2 books", doneTodo.saveFormat());
    }

    @Test
    public void testToString() {
        doneTodo.completeTask();
        assertEquals("[T] [✗]return book", unfinishedTodo.toString());
        assertEquals("[T] [✓]return 2 books", doneTodo.toString());
    }
}