package duke.Task;

import duke.Exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {

    @Test
    void stringConversion() {
        ToDo todo = new ToDo("Test");
        assertEquals("[T][✘] Test", todo.toString());
    }

    @Test
    void serializeTest() {
        ToDo todo = new ToDo("Test");
        assertEquals("T | 0 | Test", todo.serialize());
    }
}
