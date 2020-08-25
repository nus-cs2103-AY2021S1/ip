package duck.task;

import duck.ui.Colour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void todoCreated() {
        Todo todo = new Todo("read book");
        assertEquals(todo.getDone(), false);
        assertEquals(todo.getDescription(), "read book");
        assertEquals(todo.getStatus(), Colour.Yellow("[T]") + Colour.Red("[\u2718] read book"));
    }

    @Test
    public void todoCanMarkDone() {
        Todo todo = new Todo("mark as done");
        assertEquals(todo.getDone(), false);
        todo.markDone();
        assertEquals(todo.getDone(), true);
    }
}