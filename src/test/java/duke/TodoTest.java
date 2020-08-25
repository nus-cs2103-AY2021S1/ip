package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    Task task = new Todo("read book");

    @Test
    public void testToString() {
        assertEquals("[T][✘] read book", task.toString());
    }
}
