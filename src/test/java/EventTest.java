import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventToString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Event event = new Event("meeting", LocalDateTime.parse("2000-01-01 2359", formatter));
        assertEquals("[E][\u2718]meeting(at: Jan 1 2000 23:59)", event.toString());
    }

    @Test
    public void testSetDone(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Event event = new Event("return book", LocalDateTime.parse("2000-01-01 2359", formatter));
        assertEquals(false, event.getStatus());
        event.setDone();
        assertEquals(true, event.getStatus());
    }
}