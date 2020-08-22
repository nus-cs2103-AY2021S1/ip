package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event = new Event("Lunch with friends", "2020-02-23");
    Event event2 = new Event("Meeting with prof", true, "2019-05-23");

    @Test
    public void testMarkAsDone() {
        event.markAsDone();
        assertEquals("[E][✓] Lunch with friends (at: Feb 23 2020)", event.toString());

        event2.markAsDone();
        assertEquals("[E][✓] Meeting with prof (at: May 23 2019)", event2.toString());
    }

    @Test
    public void testToString() {
        assertEquals("[E][✘] Lunch with friends (at: Feb 23 2020)", event.toString());
        assertEquals("[E][✓] Meeting with prof (at: May 23 2019)", event2.toString());
    }

    @Test
    public void toCustomString() {
        assertEquals("E | 0 | Lunch with friends | 2020-02-23", event.toCustomString());
        assertEquals("E | 1 | Meeting with prof | 2019-05-23", event2.toCustomString());
    }
}