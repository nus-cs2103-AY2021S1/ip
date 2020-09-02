import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.task.Event;

public class EventTest {
    @Test
    public void event() throws EmptyDescriptionException, EmptyDateException {
        Event event = new Event("event project meeting /at 2020-08-30");
        String eventTime = event.getEventTime();
        assertEquals("Aug 30 2020", eventTime);
    }
}
