package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        Event event = new Event("project meeting", LocalDate.parse("2020-08-06", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        String expected = "[E][✗] project meeting (at: Aug 06 2020)";
        assertEquals(expected, event.toString());
    }
}
