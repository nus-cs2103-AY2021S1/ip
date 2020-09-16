package focus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testTaskToText() {
        assertEquals("T|0|iP", new ToDo("iP").taskToText());
    }
}
