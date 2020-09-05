package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {
    @Test
    public void testToDos() {
        assertEquals("T | 0 | meeting", new ToDo("meeting").writeToFile());
    }
}