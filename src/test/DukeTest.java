import duke.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    @Test
    void todo_taskToArray_test(){
        Todo todo = new Todo("shopping");
        String[] expected = new String[]{"T", "0", "shopping"};
        assertArrayEquals(expected, todo.toArray());
    }
}
