package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    private Task task = new Event("read book", "31 Dec 2020");

    @Test
    public void testToString() {
        assertEquals("[E][✘] read book (at: 31 Dec 2020)", task.toString());
    }
}
