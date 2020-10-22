package duke.duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;


public class TodoTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void todoToStringTest() {
        Todo task = new Todo("borrow book", 1, false, "N");
        assertEquals("[T][✘] borrow book ", task.toString());
    }

    @Test
    public void todoStatusTest() {
        Todo task = new Todo("borrow book", 1, false, "N");
        assertEquals("1. [T][✘] borrow book ", task.getStatusWithIndex());
    }
}
