import duke.tasks.Todo;
import duke.tasks.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    @Test
    void todo_taskToArray_test(){
        Todo todo = new Todo("shopping");
        String[] expected = new String[]{"T", "0", "shopping"};
        assertArrayEquals(expected, todo.toArray());
    }
    @Test
    void event_toArray_test(){
        Event event = new Event("meeting", "Sunday");
        String[] expected = new String[]{"D", "0", "meeting", "Sunday"};
        assertArrayEquals(expected, event.toArray());
    }
}
