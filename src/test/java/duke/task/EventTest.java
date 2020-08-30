package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    private Event ev = new Event("test", "2020-03-12 18:00");
    @Test
    public void testStringConversion() {
        assertEquals("[E][\u2718] test (at: Mar 12 2020 6:00 PM)", ev.toString());
    }

}
