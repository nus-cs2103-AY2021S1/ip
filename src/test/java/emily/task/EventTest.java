package emily.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testToString() {
        LocalDateTime date = LocalDateTime.parse("2020-01-10 1430",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        assertEquals("[E][" + "\u2718" + "] book reading  (at: 10-Jan-2020 14:30)",
                new Event("book reading ", "2020-01-10 1430").toString());
    }
}
