import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import shiro.task.Event;

public class EventTest {
    @Test
    public void event() {
        Event event = new Event("event project meeting /at 2020-08-30");
        String encodedEvent = event.encode();
        assertEquals("E | 0 | project meeting | 2020-08-30", encodedEvent);
    }
}
