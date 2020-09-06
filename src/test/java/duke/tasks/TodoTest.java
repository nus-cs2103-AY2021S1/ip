package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

class TodoTest {

    @Test
    public void testCreateTodo() throws DukeException {
        Todo todo = new Todo("Test content", "0");
    }

    @Test
    public void testTodoSetComplete() throws DukeException {
        Todo todo = new Todo("Test content", "0");
        todo.setComplete(true);
        assertTrue(todo.isComplete);
    }

    @Test
    public void testIncompleteTodoToString() throws DukeException {
        Todo todo = new Todo("Test content", "0");
        assertEquals("[T][X](!) Test content", todo.toString());
    }

    @Test
    public void testCompleteTodoToString() throws DukeException {
        Todo todo = new Todo("Test content", "0");
        todo.setComplete(true);
        assertEquals("[T][Y](!) Test content", todo.toString());
    }

    @Test
    public void testIncompleteTodoToSaveString() throws DukeException {
        Todo todo = new Todo("Test content", "0");
        assertEquals("T/0/0/Test content/ ", todo.toSaveString());
    }

    @Test
    public void testCompleteTodoToSaveString() throws DukeException {
        Todo todo = new Todo("Test content", "0");
        todo.setComplete(true);
        assertEquals("T/0/1/Test content/ ", todo.toSaveString());
    }

}
