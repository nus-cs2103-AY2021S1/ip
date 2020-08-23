package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event ev = new Event("test", "2020-03-12 18:00");
    @Test
    public void testStringConversion() {
        assertEquals("[E][✘] test (at: Mar 12 2020 6:00 PM)", ev.toString());
    }

}
