import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void todo() throws EmptyDescriptionException {
        ToDo todo = new ToDo("todo read book");
        todo.markAsDone();
        assertEquals(true, todo.isDone());
    }
}
