import duke.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testGetStatusIcon() {
        assertEquals("not done", new ToDo("sleep").getStatus());
    }

    @Test
    public void testGetDescription() {
        assertEquals("sleep", new ToDo("sleep").getDescription());
    }

}
