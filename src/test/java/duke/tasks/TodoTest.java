package duke.tasks;

import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TodoTest {

    @Test
    public void constructorTest() {
        Todo todoTask = new Todo("read book");
        assertEquals("[T][\u2718] read book", todoTask.toString());
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
        assertEquals("[T][\u2713] read book", todoTask.toString());
    }
}