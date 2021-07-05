package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ToDoTest {
    @Test
    public void testToDos() {
        assertEquals("T | 0 | meeting", new ToDo("meeting").writeToFile());
    }
}
