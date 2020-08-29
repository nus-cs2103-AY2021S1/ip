package test.java.duke.tasks;

import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TodoTest {

    @Test
    public void constructorTest() {
        Todo todoTask = new Todo("read book");
        assertEquals("[T][✘] read book", todoTask.toString());
    }

    @Test
    void getState() {
        Todo todoTask = new Todo("read book");
        assertEquals("T|0|read book", todoTask.getState());
    }

    @Test
    void doneTask() {
        Todo todoTask = new Todo("read book");
        todoTask.markAsDone();
        assertEquals("[T][✓] read book", todoTask.toString());
    }
}