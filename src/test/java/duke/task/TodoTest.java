package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {

    @Test
    public void createTask_Success() throws DukeException {
        Todo t = Todo.createTask("toDo Buy Books");
        assertEquals("Buy Books", t.description);
    }

    @Test
    public void createTask_missingDescription_Failure() {
        try {
            Todo.createTask("toDo  ");
            fail();
        } catch (DukeException e) {
            String err = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n"
                    + " Oops!! You forgot to tell me what this task is about... *woof*\n"
                    + ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
            assertEquals(err, e.getMessage());
        }
    }

    @Test
    public void task_checkFormatPrint() throws DukeException {
        Todo t = Todo.createTask("toDo        BuY BooKs        ");
        assertEquals("BuY BooKs", t.description);
    }

    @Test
    public void toStringTest() throws DukeException {
        Todo t = Todo.createTask("toDo        BuY BooKs        ");
        assertEquals("[T][✘] BuY BooKs", t.toString());
    }

    @Test
    public void createTaskWithDoneTest() {
        Todo t = new Todo("buy bread", true);
        assertEquals("[T][✓] buy bread", t.toString());
    }
}
