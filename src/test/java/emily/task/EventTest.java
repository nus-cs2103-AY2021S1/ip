package emily.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import main.java.emily.task.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @org.junit.jupiter.api.Test
    void testToString() {
        LocalDateTime date = LocalDateTime.parse("2020-01-10 1430",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        assertEquals("[E][" + "\u2718" + "] book reading (at: 2020-01-10 14:30)",
                new Event("book reading ", "2020-01-10 1430").toString());
    }
}