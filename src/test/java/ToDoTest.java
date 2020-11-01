import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Task;
import duke.ToDo;


public class ToDoTest {
    private Task newToDoTask = new ToDo("read book");

    @Test
    void getTaskTypeTest() {
        assertEquals("ToDo", newToDoTask.getTaskType());
    }

    @Test
    public void toStringTest() {
        assertEquals("[T][✘] read book", newToDoTask.toString());
    }

    @Test
    public void toStringInFile() {
        assertEquals("T | 0 | read book", newToDoTask.toStringInFile());
    }
}
