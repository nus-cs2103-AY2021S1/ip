import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import shiro.exception.EmptyDescriptionException;
import shiro.task.ToDo;

public class ToDoTest {
    @Test
    public void todo() throws EmptyDescriptionException {
        ToDo todo = new ToDo("todo read book");
        todo.markAsDone();
        assertEquals(true, todo.isDone());
    }
}
