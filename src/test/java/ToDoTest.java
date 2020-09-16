import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testGetStatusIcon() {
        assertEquals("X", new ToDo("sleep").getStatusIcon());
    }

    @Test
    public void testGetDescription() {
        assertEquals("sleep", new ToDo("sleep").getDescription());
    }

}
