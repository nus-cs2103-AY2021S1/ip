import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ToDo;

public class ToDoTest {
    @Test
    public void testGetStatusIcon() {
        assertEquals("\u2718", new ToDo("sleep").getStatusIcon());
    }

    @Test
    public void testGetDescription() {
        assertEquals("sleep", new ToDo("sleep").getDescription());
    }

}
