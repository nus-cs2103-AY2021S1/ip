package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void newToDo_validArguments_toDoObjectReturned() {
        try {
            new ToDo("read book");
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void newToDo_validArgumentsWithIsDoneSet_toDoObjectReturned() {
        try {
            new ToDo("read book", true);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toStringForMemory_taskNotDone() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T]|0|read book", todo.toStringForMemory());
    }

    @Test
    public void toStringForMemory_taskDone() {
        ToDo todo = new ToDo("read book", true);
        assertEquals("[T]|1|read book", todo.toStringForMemory());
    }

    @Test
    public void toStringForGui_taskNotDone() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][\u2718] read book", todo.toStringForGui());
    }

    @Test
    public void toStringForGui_taskDone() {
        ToDo todo = new ToDo("read book", true);
        assertEquals("[T][\u2713] read book", todo.toStringForGui());
    }

    @Test
    public void toStringForCli_taskNotDone() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][✘] read book", todo.toStringForCli());
    }

    @Test
    public void toStringForCli_taskDone() {
        ToDo todo = new ToDo("read book", true);
        assertEquals("[T][✓] read book", todo.toStringForCli());
    }
}
