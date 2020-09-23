package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToString() {
        ToDo toDo = new ToDo("read book");
        String expected = "[T][X] read book";
        assertEquals(expected, toDo.toString());
    }
}
