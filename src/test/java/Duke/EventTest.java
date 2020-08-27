package duke;

import task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests if the Event Task is correctly implemented.
 *
 * @author Joshua
 */
public class EventTest {

    LocalDateTime dateTime = LocalDateTime.parse("2020-02-20T23:59");
    Event unfinishedEvent = new Event("return book ");
    Event doneEvent = new Event("return 2 books ");

    @Test
    public void testSaveFormat() {
        unfinishedEvent.setTime(dateTime);
        doneEvent.setTime(dateTime);
        doneEvent.completeTask();
        assertEquals("[E] [✗]return book at:20/02/2020 2359", unfinishedEvent.saveFormat());
        assertEquals("[E] [✓]return 2 books at:20/02/2020 2359", doneEvent.saveFormat());
    }

    @Test
    public void testToString() {
        unfinishedEvent.setTime(dateTime);
        doneEvent.setTime(dateTime);
        doneEvent.completeTask();
        assertEquals("[E] [✗]return book (at:Feb 20 2020 23:59PM)", unfinishedEvent.toString());
        assertEquals("[E] [✓]return 2 books (at:Feb 20 2020 23:59PM)", doneEvent.toString());
    }
}