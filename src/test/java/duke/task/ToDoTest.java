package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testTaskToText() {
        assertEquals("T|0|iP", new ToDo("iP").taskToText());
    }
}
