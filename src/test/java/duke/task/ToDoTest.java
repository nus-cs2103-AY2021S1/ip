package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        ToDo task = new ToDo("read book");
        assertEquals("[T][\u2718] read book", task.toString());
        task.done();
        assertEquals("[T][\u2713] read book", task.toString());
    }
}