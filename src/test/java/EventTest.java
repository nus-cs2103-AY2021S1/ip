import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void event() throws EmptyDescriptionException {
        Event event = new Event("event project meeting /at 2020-08-30");
        String eventTime = event.getEventTime();
        assertEquals("Aug 30 2020", eventTime);
    }
}
