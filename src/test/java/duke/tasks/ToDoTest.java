package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testToString() {
        ToDo toDo = new ToDo("read book");
        String expected = "[T][✗] read book";
        assertEquals(expected, toDo.toString());
    }
}
