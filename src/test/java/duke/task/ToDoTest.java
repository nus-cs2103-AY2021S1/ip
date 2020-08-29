package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void createTodo_withDescription_stringReturned() {
        ToDo test = new ToDo("Walk Dog");
        assertEquals("T | 0 | Walk Dog", test.toString());
    }

    @Test
    public void createTodo_withOutDescription_stringReturned() {
        ToDo test = new ToDo("");
        assertEquals("T | 0 | ", test.toString());
    }
}
