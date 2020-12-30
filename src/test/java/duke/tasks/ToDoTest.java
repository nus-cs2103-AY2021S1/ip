package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ToDoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][✘] join sports club", new ToDo("join sports club").toString());
    }
}
