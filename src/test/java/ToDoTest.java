import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ToDoTest {

    @Test
    public void testToDoWriteSaveFormat() {
        ToDo toDoTask = new ToDo("read book");
        String actualFormat = "T | 0 | read book | ";
        assertEquals(actualFormat, toDoTask.writeSaveFormat());

    }

    @Test
    public void testCreateToDo() {
        ToDo toDoTask = new ToDo("run");
        String actualFormat = "[T][\u2718] run";
        assertEquals(actualFormat, toDoTask.toString());

    }
}