package duke;

import main.java.duke.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testingStringConversion() {
        Event event = new Event("return book", "2020-05-24");
        assertEquals("[E][✘] return book (at: May 24 2020)", event.toString());
        event.markAsDone();
        assertEquals("[E][✓] return book (at: May 24 2020)", event.toString());
    }

    @Test
    public void testingStringFileConversion() {
        Event event = new Event("return book", "2020-05-24");
        assertEquals("E | 0 | return book | 2020-05-24", event.toStringFile()); 
    }
}
