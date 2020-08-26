package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void txtFileFormatTest() {
        Event event = new Event("eventtest", LocalDate.parse("2020-12-25"),
                LocalTime.parse("18:00"), LocalTime.parse("22:00"));
        assertEquals("E ~/~ 0 ~/~ eventtest ~/~ 2020-12-25 ~/~ 18:00 ~/~ 22:00", event.txtFileFormat());
    }
}
