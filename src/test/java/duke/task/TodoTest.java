package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo todo = new Todo("todo1", false);
        assertEquals("[T][✘] todo1", todo.toString());
    }

    @Test
    public void completeTest() {
        Todo nonCompletedTodo = new Todo("todo1", false);
        Todo completedTodo = new Todo("todo1", true);
        assertEquals(completedTodo.toString(), nonCompletedTodo.complete().toString());
    }

    @Test
    public void formatTest() {
        Todo todo = new Todo("todo1", false);
        assertEquals("T | 0 | todo1", todo.format());
    }
}

