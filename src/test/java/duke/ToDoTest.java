package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        ToDo toDo = new ToDo("test");
        assertEquals("[T]|[\u2718] | test", toDo.toString());
    }
}
