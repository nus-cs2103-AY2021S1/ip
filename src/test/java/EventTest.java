import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exception.EmptyDateException;
import org.junit.jupiter.api.Test;

import duke.exception.EmptyDescriptionException;
import duke.task.Event;

public class EventTest {
    @Test
    public void event() throws EmptyDescriptionException, EmptyDateException {
        Event event = new Event("event project meeting /at 2020-08-30");
        String encodedEvent = event.encode();
        assertEquals("E | 0 | project meeting | 2020-08-30", encodedEvent);
    }
}
