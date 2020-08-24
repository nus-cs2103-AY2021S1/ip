import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event = new Event("read books", "2020-08-25 4:00 PM");
    
    @Test
    public void testToString() {
        assertEquals("[E][✘] read books (at: 2020-08-25 4:00 PM)", event.toString());
    }

    @Test
    public void testGetData() {
        assertEquals("e/0/read books/2020-08-25 4:00 PM", event.getData());
    }
}
