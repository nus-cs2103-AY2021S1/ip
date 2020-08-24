package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

class EventsTest {
    @Test
    public void testEvents() {
        assertEquals("E | 0 | meeting | 2020-08-16", new Events("meeting", "2020-08-16").writeToFile());
    }
}