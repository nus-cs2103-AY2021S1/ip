package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TodoTest {

    @Test
    public void createTask_success() throws DukeException {
        Todo t = Todo.createTask("toDo Buy Books");
        assertEquals("Buy Books", t.description);
    }

    @Test
    public void createTask_missingDescription_failure() {
        try {
            Todo.createTask("toDo  ");
            fail();
        } catch (DukeException e) {
            String err = " Oops!! You forgot to tell me what this task is about... *woof*\n";
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
