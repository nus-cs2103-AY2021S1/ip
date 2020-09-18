package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo("arrange meeting");
        assertEquals("[T][X] arrange meeting", todo.toString());
    }

    @Test
    public void testCompleteTask() {
        Todo todo = new Todo("read book");
        todo.completeTask();
        assertEquals("[T][V] read book", todo.toString());
    }

    @Test
    public void testGetData() {
        Todo todo = new Todo("home workout");
        assertEquals("TODO#home workout#false#", todo.getData());
    }
}
