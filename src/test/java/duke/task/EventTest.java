package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        String expected = "[E][✘] eat (at: 22 February 2020, 12:30 PM)";
        assertEquals(expected, new Event("eat", "22/02/2020 12:30").toString());
    }

    @Test
    public void testToFileString() {
        String expected = "E | 0 | eat | 22/02/2020 00:00";
        assertEquals(expected, new Event("eat", "22/02/2020 00:00").toFileString());
    }
}

