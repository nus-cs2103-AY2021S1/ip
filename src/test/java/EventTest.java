import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    public void testEventWriteSaveFormat() {
        Event eventTask = new Event("project meeting", "01-01-2020 12:30");
        String actualFormat = "E | 0 | project meeting | 01-01-2020 12:30 | ";
        assertEquals(actualFormat, eventTask.writeSaveFormat());
    }

    @Test
    public void testCreateEvent() {
        Event eventTask = new Event("project meeting", "01-01-2020 12:30");
        String actualFormat = "[E][\u2718] project meeting (at: 1 Jan 2020, 12:30 PM)";
        assertEquals(actualFormat, eventTask.toString());
    }

}