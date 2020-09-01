package duck.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void todoCreated() {
        Todo todo = new Todo("read book");
        assertEquals(false, todo.getDone());
        assertEquals("read book", todo.getDescription());
        assertEquals("[T][\u2718] read book", todo.getStatus());
    }

    @Test
    public void todoCanMarkDone() {
        Todo todo = new Todo("mark as done");
        assertEquals(false, todo.getDone());
        todo.markDone();
        assertEquals(true, todo.getDone());
    }
}
