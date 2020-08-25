package duke;

import duke.tasks.Event;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    public static DateTimeFormatter VALID_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void markDoneTest() {
        Event e = new Event("party", LocalDateTime.parse("2020-10-10 18:59", VALID_FORMAT), false);
        assertEquals(e.markDone().toString(), "[E][✓] party (at: Oct 10 2020 18:59)");
    }

    @Test
    public void fileFormatetedStringTest() {
        Event e = new Event("party", LocalDateTime.parse("2020-10-10 18:59", VALID_FORMAT), false);
        assertEquals(e.fileFormattedString(), "E | 0 | party | Oct 10 2020 18:59");
    }
}
