package duke.tasks;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoTest {

    @Test
    public void testCreateTodo() throws DukeException {
        Todo todo = new Todo("Test content");
    }

    @Test
    public void testTodoSetComplete() throws DukeException {
        Todo todo = new Todo("Test content");
        todo.setComplete(true);
        assertTrue(todo.isComplete);
    }

    @Test
    public void testIncompleteTodoToString() throws DukeException {
        Todo todo = new Todo("Test content");
        assertEquals("[T][X] Test content", todo.toString());
    }

    @Test
    public void testCompleteTodoToString() throws DukeException {
        Todo todo = new Todo("Test content");
        todo.setComplete(true);
        assertEquals("[T][Y] Test content", todo.toString());
    }

    @Test
    public void testIncompleteTodoToSaveString() throws DukeException {
        Todo todo = new Todo("Test content");
        assertEquals("T/0/Test content/ ", todo.toSaveString());
    }

    @Test
    public void testCompleteTodoToSaveString() throws DukeException {
        Todo todo = new Todo("Test content");
        todo.setComplete(true);
        assertEquals("T/1/Test content/ ", todo.toSaveString());
    }

}
