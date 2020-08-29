package duke.duke.tasks;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;


public class TodoTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    private void assertEquals(int i, int i1) {
    }

    @Test
    public void todoToStringTest() {
        Todo task = new Todo("borrow book", 1, false);
        assert task.toString().equals("[T][✘] borrow book");
    }

    @Test
    public void todoStatusTest() {
        Todo task = new Todo("borrow book", 1, false);
        assert task.getStatusWithIndex().equals("1. [T][✘] borrow book");
    }
}
