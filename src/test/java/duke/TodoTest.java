package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    private Task task = new Todo("read book");

    @Test
    public void testToString() {
        assertEquals("[T][✘] read book", task.toString());
    }
}
